package com.project.novriani.service.cmeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.novriani.bean.Cluster;
import com.project.novriani.bean.Record;
import com.project.novriani.model.Enroll;
import com.project.novriani.model.Lesson;
import com.project.novriani.model.Student;
import com.project.novriani.model.StudentClassroom;
import com.project.novriani.service.LessonService;
import com.project.novriani.service.StudentService;

@Service
public class CMeansService {

	@Autowired
	private StudentService studentService;

	@Autowired
	private LessonService lessonService;

	private float u_pre[][];

	public void run(int clusterNumber, int maxIteration, int fuzziness, double epsilon) {
		double finalError;
		List<Student> students = studentService.getStudentAll();
		int numberOfData = (int) students.stream().filter(p -> {
			return p.getStudentClassrooms().stream().filter(q -> {
				return !q.getEnrolls().isEmpty();
			}).findAny().isPresent();
		}).count();

		List<Lesson> lessons = lessonService.getLessonAll();
		int dimension = lessons.size();

		int minRange = 1;
		int maxRange = 100;
		ArrayList<ArrayList<Float>> clusterCenters = new ArrayList<>();

		ArrayList<ArrayList<Float>> data = createRandomData(numberOfData, dimension, minRange, maxRange, clusterNumber);
		float[][] u = assignInitialMembership(data, clusterNumber);
		for (int i = 0; i < maxIteration; i++) {
			clusterCenters = calculateClusterCenters(data, u, clusterNumber, dimension, fuzziness);
			updateMembershipValues(data, u, clusterNumber, fuzziness, clusterCenters);
			finalError = checkConvergence(data, u, clusterNumber);
			if (finalError <= epsilon) {
				break;
			}
		}

		System.out.println("=======cluster center======");
		clusterCenters.stream().forEach(p -> {
			System.out.println(p);
		});
//		System.out.println("=======member u ======");
//		for (int i = 0; i < u.length; i++) {
//			float max = 0;
//			
//			for (int j = 0; j < clusterNumber; j++) {
//				if (max == 0)
//					max = u[i][j];
//
//				if (u[i][j] > max) {
//					max = u[i][j];
//				}
//				
//				System.out.println("matrix[" + i + "][" + j + "]: " + u[i][j]);
//			}
//			
//			System.out.println("Max: " + max);
//		}

//		System.out.println("=======assign to clusters======");
//		assignToClusters(clusterCenters, students);

	}

	private void assignToClusters(ArrayList<ArrayList<Float>> clusterCenters, List<Student> students) {
		for (int i = 0; i < clusterCenters.size(); i++) {
			for (int j = 0; j < clusterCenters.get(i).size(); j++) {

			}
		}

	}

	public ArrayList<ArrayList<Float>> createRandomData(int numberOfData, int dimension, int minRange, int maxRange,
			int clusterNumber) {
		ArrayList<ArrayList<Float>> data = new ArrayList<ArrayList<Float>>();
		ArrayList<ArrayList<Integer>> centroids = new ArrayList<>();
		centroids.add(new ArrayList<Integer>());
		int[] numberOfDataInEachArea = new int[clusterNumber];
		int range = maxRange - minRange + 1;
		int step = range / (clusterNumber + 1);

		for (int i = 1; i <= clusterNumber; i++) {
			centroids.get(0).add(minRange + i * step);
		}

		for (int i = 0; i < dimension - 1; i++) {
			centroids.add((ArrayList<Integer>) centroids.get(0).clone());
		}

		for (int i = 0; i < dimension; i++) {
			Collections.shuffle(centroids.get(i));
		}

		Random r = new Random();
		int sum = 0;
		for (int i = 0; i < clusterNumber; i++) {
			int rg = r.nextInt(50) + 10;
			numberOfDataInEachArea[i] = (rg);
			sum += rg;
		}

		for (int i = 0; i < clusterNumber; i++)
			numberOfDataInEachArea[i] = (int) ((((double) numberOfDataInEachArea[i]) / sum) * numberOfData);

//		for (int i = 0; i < numberOfDataInEachArea.length; i++) {
//			System.out.println(numberOfDataInEachArea[i]);
//		}

		double variance = (centroids.get(0).get(1) - centroids.get(0).get(0)) / 2.5;
		Random fRandom = new Random();
		for (int i = 0; i < clusterNumber; i++) {
			for (int j = 0; j < numberOfDataInEachArea[i]; j++) {
				ArrayList<Float> tmp = new ArrayList<>();
				for (int k = 0; k < dimension; k++) {
					tmp.add((float) (centroids.get(k).get(i) + fRandom.nextGaussian() * variance));
				}
				data.add(tmp);
			}
		}
//
//		System.out.println("======centroids======");
//		centroids.stream().forEach(p -> {
//			System.out.println(p);
//		});
//
//		System.out.println("======data======");
//		data.stream().forEach(p -> {
//			System.out.println(p);
//		});

		return data;
	}

	/**
	 * this function generate membership value for each data
	 */
	private float[][] assignInitialMembership(ArrayList<ArrayList<Float>> data, int clusterNumber) {
		float[][] u = new float[data.size()][clusterNumber];
		u_pre = new float[data.size()][clusterNumber];
		Random r = new Random();
		for (int i = 0; i < data.size(); i++) {
			float sum = 0;
			for (int j = 0; j < clusterNumber; j++) {
				u[i][j] = r.nextFloat() * 10 + 1;
				sum += u[i][j];
			}

			for (int j = 0; j < clusterNumber; j++) {
				u[i][j] = u[i][j] / sum;
//				System.out.println("matrix[" + i + "][" + j + "]: " + u[i][j]);
			}
//			System.out.println("sum[" + i + "]: " + sum);
		}

		return u;
	}

	/**
	 * in this function we calculate value of each cluster
	 */
	private ArrayList<ArrayList<Float>> calculateClusterCenters(ArrayList<ArrayList<Float>> data, float[][] u,
			int clusterNumber, int dimension, int fuzziness) {
		ArrayList<ArrayList<Float>> clusterCenters = new ArrayList<>();
		for (int i = 0; i < clusterNumber; i++) {
			ArrayList<Float> tmp = new ArrayList<>();
			for (int j = 0; j < dimension; j++) {
				float cluster_ij;
				float sum1 = 0;
				float sum2 = 0;
				for (int k = 0; k < data.size(); k++) {
					double tt = Math.pow(u[k][i], fuzziness);
					sum1 += tt * data.get(k).get(j);
					sum2 += tt;
				}
				cluster_ij = sum1 / sum2;
				tmp.add(cluster_ij);
			}
			clusterCenters.add(tmp);
		}

//		System.out.println("=======cluster center======");
//		clusterCenters.stream().forEach(p -> {
//			System.out.println(p);
//		});

		return clusterCenters;
	}

	/**
	 * in this function we will update membership value
	 */
	private void updateMembershipValues(ArrayList<ArrayList<Float>> data, float[][] u, int clusterNumber, int fuzziness,
			ArrayList<ArrayList<Float>> clusterCenters) {
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < clusterNumber; j++) {
				u_pre[i][j] = u[i][j];
				float sum = 0;
				float upper = Distance(data.get(i), clusterCenters.get(j));
				for (int k = 0; k < clusterNumber; k++) {
					float lower = Distance(data.get(i), clusterCenters.get(k));
					sum += Math.pow((upper / lower), 2 / (fuzziness - 1));
				}
				u[i][j] = 1 / sum;
			}
		}
	}

	/**
	 * get norm 2 of two point
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private float Distance(ArrayList<Float> p1, ArrayList<Float> p2) {
		float sum = 0;
		for (int i = 0; i < p1.size(); i++) {
			sum += Math.pow(p1.get(i) - p2.get(i), 2);
		}
		sum = (float) Math.sqrt(sum);
		return sum;
	}

	/**
	 * we calculate norm 2 of ||U - U_pre||
	 * 
	 * @return
	 */
	private double checkConvergence(ArrayList<ArrayList<Float>> data, float[][] u, int clusterNumber) {
		double sum = 0;
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < clusterNumber; j++) {
				sum += Math.pow(u[i][j] - u_pre[i][j], 2);
			}
		}
		return Math.sqrt(sum);
	}

}
