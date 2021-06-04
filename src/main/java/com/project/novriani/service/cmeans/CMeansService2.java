package com.project.novriani.service.cmeans;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.QAbstractAuditable;
import org.springframework.stereotype.Service;

import com.project.novriani.bean.ClusterCenter;
import com.project.novriani.bean.ClusterCenter2D;
import com.project.novriani.bean.Matrix;
import com.project.novriani.bean.Matrix2D;
import com.project.novriani.model.Enroll;
import com.project.novriani.model.Lesson;
import com.project.novriani.model.Student;
import com.project.novriani.model.StudentClassroom;
import com.project.novriani.service.LessonService;
import com.project.novriani.service.StudentService;

@Service
public class CMeansService2 {

	@Autowired
	private StudentService studentService;

	@Autowired
	private LessonService lessonService;

	public double[][] run(int clusterNumber, int maxIteration, int fuzziness, double epsilon) {
//		int clusterNumber = 2;
//		int maxIteration = 100;
//		int fuzziness = 2;
//		double epsilon = 0.01;
		double finalError;

		List<Student> students = studentService.getStudentAll();
		int totalData = (int) students.stream().filter(p -> {
			return p.getStudentClassrooms().stream().filter(q -> {
				return !q.getEnrolls().isEmpty();
			}).findAny().isPresent();
		}).count();
		totalData = 3;

		List<Lesson> lessons = lessonService.getLessonAll();

		double[][] matrix = generateRandomMatrixPartition(clusterNumber, totalData, students);
		double[][] clusterCenters = calcClusterCenter(clusterNumber, fuzziness, lessons, totalData, students, matrix);
		double[][] totalFO = new double[totalData][clusterNumber];
		double fBaru = objectiveFunction(clusterNumber, fuzziness, lessons, totalData, students, matrix, clusterCenters,
				totalFO);
//		double[][] newMatrix = generateNewMatrixPartition(clusterNumber, fuzziness, lessons, totalData, totalFO);
//		matrix = newMatrix;

//		for (int i = 0; i < totalData; i++) {
//			for (int j = 0; j < clusterNumber; j++) {
//				System.out.println("matrix[" + i + "][" + j + "]: " + matrix[i][j]);
//			}
//		}

		return matrix;

	}

	private double[][] generateRandomMatrixPartition(int clusterNumber, int totalData, List<Student> students) {
		double matrix[][] = new double[totalData][clusterNumber];
		Random random = new Random();
		for (int i = 0; i < totalData; i++) {
			float total = 0;
			double result = 0;
			for (int j = 0; j < clusterNumber; j++) {
				matrix[i][j] = (Math.round(random.nextDouble() * 100.0));
				total += matrix[i][j];
//				System.out.println("======loop random======");
//				System.out.println("matrix[" + i + "][" + j + "]: " + matrix[i][j]);
			}

			// normalisasi matrix partisi
			for (int j = 0; j < clusterNumber; j++) {
				matrix[i][j] /= total;
//				System.out.println("======loop set matrix======");
				System.out.println("matrix[" + i + "][" + j + "]: " + matrix[i][j]);
				result += matrix[i][j];
			}

//			System.out.println("total [" + i + "]: " + total);
//			System.out.println("result [" + i + "]: " + result);
		}

		return matrix;
	}

	private double[][] calcClusterCenter(int clusterNumber, int fuzziness, List<Lesson> lessons, int totalData,
			List<Student> students, double[][] matrix) {
		int totalAttributes = lessons.size();
		double clusterCenters[][] = new double[clusterNumber][totalAttributes];
		double temp[][] = new double[clusterNumber][totalAttributes];

		for (int i = 0; i < clusterNumber; i++) {
//			System.out.println("cluster " + i);
			for (int j = 0; j < totalAttributes; j++) {
				int attr = j;
				double a = 0;
				double b = 0;
				for (int k = 0; k < totalData; k++) {
					// get score
					List<Enroll> enrolls = new ArrayList<>();
					students.get(k).getStudentClassrooms().stream().forEach(p -> {
						enrolls.addAll(p.getEnrolls());
					});

//					System.out.println(students.get(k).toString());
					int score = enrolls.stream().filter(p -> p.getLesson().getId() == lessons.get(attr).getId())
							.mapToInt(Enroll::getScore).sum();
//					System.out.println("Student: " + students.get(k).getStudentName() + ", Lesson id-desc: " + lessons.get(attr).getId() + "-"
//							+ lessons.get(attr).getLessonName() + ", Score: " + score);
//					System.out.println(matrix[k][i]);
					b += (Math.pow(matrix[k][i], fuzziness) * 1);
					a += (b * score);

//					System.out.println("a: " + a + ", b: " + b);
				}
//				System.out.println("Total a: " + a + ", b: " + b);
				temp[i][j] = a / b;
				clusterCenters[i][j] = temp[i][j];

				System.out.println("clusterCenter dari nilai[" + i + "][" + j + "]: " + clusterCenters[i][j]);
//				System.out.println("===================================");
			}
		}

		return clusterCenters;
	}

	private double objectiveFunction(int clusterNumber, int fuzziness, List<Lesson> lessons, int totalData,
			List<Student> students, double[][] matrix, double[][] clusterCenters, double[][] totalFO) {
		int totalAttributes = lessons.size();
		double fBaru = 0;
		double[][] hitungFO = new double[totalData][clusterNumber];

		for (int i = 0; i < totalData; i++) {
//			double sumTotalFO = 0;
			for (int j = 0; j < clusterNumber; j++) {
				for (int k = 0; k < totalAttributes; k++) {
					int attr = k;
					List<Enroll> enrolls = new ArrayList<>();
					students.get(i).getStudentClassrooms().stream().forEach(p -> {
						enrolls.addAll(p.getEnrolls());
					});
					int score = enrolls.stream().filter(p -> p.getLesson().getId() == lessons.get(attr).getId())
							.mapToInt(Enroll::getScore).sum();

					System.out.println("Student: " + students.get(i).getStudentName() + ", Lesson id-desc: "
							+ lessons.get(attr).getId() + "-" + lessons.get(attr).getLessonName() + ", Score: "
							+ score);
					System.out.println("clusterCenter[" + j + "][" + k + "]: " + +clusterCenters[j][k]);
					totalFO[i][j] += Math.pow((score - clusterCenters[j][k]), fuzziness);
					System.out.println("totalFO[" + i + "][" + j + "]: " + totalFO[i][j]);
//					sumTotalFO += totalFO[i][j];
				}
//				System.out.println("sumTotalFO: " + sumTotalFO);
				System.out.println("Total totalFO[" + i + "][" + j + "]: " + totalFO[i][j]);
				hitungFO[i][j] = totalFO[i][j] * Math.pow(matrix[i][j], fuzziness);
				System.out.println("nilai hitung sementara fungsi objektif[" + i + "][" + j + "]: " + hitungFO[i][j]);
				fBaru += hitungFO[i][j];
			}
		}
		System.out.println("nilai fungsi objektif: " + fBaru);
		return fBaru;
	}

	private double[][] generateNewMatrixPartition(int clusterNumber, int fuzziness, List<Lesson> lessons, int totalData,
			double[][] totalFO) {
		double pow = -1 / (fuzziness - 1);
		double[] total = new double[totalData];
		double[][] newMatrix = new double[totalData][clusterNumber];
		double result = 0;
		
		int totalAttribute = lessons.size();

		// set a
		for (int i = 0; i < totalData; i++) {
			for (int j = 0; j < clusterNumber; j++) {
				total[i] += Math.pow(totalFO[i][j], pow);
			}
		}

		for (int i = 0; i < totalData; i++) {
			for (int j = 0; j < clusterNumber; j++) {
				newMatrix[i][j] = Math.pow(totalFO[i][j], pow) / total[i];
				result += newMatrix[i][j];
				System.out.println("newMatrix[" + i + "][" + j + "]: " + newMatrix[i][j]);
			}

//			System.out.println("result[" + i + "]" + result);
			result = 0;
		}

		return newMatrix;
	}

	private double checkConvergence(int clusterNumber, int totalData, int fuzziness, List<Matrix> matrixes,
			List<Matrix> newMatrixes) {
		double sum = 0;
		for (int i = 0; i < totalData; i++) {
			int row = i;
			for (int j = 0; j < clusterNumber; j++) {
				int col = j;
				double valueNewMatrix = newMatrixes.stream().filter(p -> p.getRow() == row)
						.flatMap(q -> q.getColumns().stream().filter(r -> r.getColumn() == col))
						.mapToDouble(Matrix2D::getValue).sum();
				double matrix = newMatrixes.stream().filter(p -> p.getRow() == row)
						.flatMap(q -> q.getColumns().stream().filter(r -> r.getColumn() == col))
						.mapToDouble(Matrix2D::getValue).sum();

				sum += Math.pow(valueNewMatrix - matrix, fuzziness);
			}
		}
		return Math.sqrt(sum);
	}

}
