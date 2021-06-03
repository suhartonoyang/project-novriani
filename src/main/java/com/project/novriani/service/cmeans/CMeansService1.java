package com.project.novriani.service.cmeans;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.QAbstractAuditable;
import org.springframework.stereotype.Service;

import com.project.novriani.model.Enroll;
import com.project.novriani.model.Lesson;
import com.project.novriani.model.Student;
import com.project.novriani.model.StudentClassroom;
import com.project.novriani.service.LessonService;
import com.project.novriani.service.StudentService;

@Service
public class CMeansService1 {

	@Autowired
	private StudentService studentService;

	@Autowired
	private LessonService lessonService;

	public void run() {
		int clusterNumber = 2;
		int maxIteration = 100;
		int fuzziness = 2;
		double epsilon = 0.01;
		double finalError;

		List<Student> students = studentService.getStudentAll();
		int totalData = (int) students.stream().filter(p -> {
			return p.getStudentClassrooms().stream().filter(q -> {
				return !q.getEnrolls().isEmpty();
			}).findAny().isPresent();
		}).count();
		totalData = 10;

		List<Lesson> lessons = lessonService.getLessonAll();

		double[][] matrix = generateRandomMatrixPartition(clusterNumber, totalData);
		double[][] newMatrix = null;
		for (int i = 0; i < maxIteration; i++) {
			double[][] clusterCenters = calcClusterCenter(clusterNumber, fuzziness, lessons, totalData, students, matrix);
			double[][] totalFO = new double[totalData][clusterNumber];
			double fBaru = objectiveFunction(clusterNumber, fuzziness, lessons, totalData, students, matrix, clusterCenters,
					totalFO);
			newMatrix = generateNewMatrixPartition(clusterNumber, fuzziness, lessons, totalData, totalFO);
		
			finalError = checkConvergence(clusterNumber, totalData, matrix, newMatrix);
//			System.out.println("fBaru: " + fBaru);
//			System.out.println("finalError: " + finalError);
			if (finalError <= epsilon) {
//				System.out.println("break di loop " + i);
				break;
			}
		}
		
		for (int i = 0; i < totalData; i++) {
			for (int j = 0; j < clusterNumber; j++) {
				System.out.println("matrix[" + i + "][" + j + "]: " + newMatrix[i][j]);
			}
		}
		
	}

	private double[][] generateRandomMatrixPartition(int clusterNumber, int totalData) {
		double membershipValue[][] = new double[totalData][clusterNumber];
		Random random = new Random();
		for (int i = 0; i < totalData; i++) {
			float total = 0;
			double result = 0;
			for (int j = 0; j < clusterNumber; j++) {
				membershipValue[i][j] = (Math.round(random.nextDouble() * 100.0));
				total += membershipValue[i][j];
//				System.out.println("======loop random======");
//				System.out.println("membershipValue[" + i + "][" + j + "]: " + membershipValue[i][j]);
			}

			// normalisasi matrix partisi
			for (int j = 0; j < clusterNumber; j++) {
				membershipValue[i][j] /= total;
//				System.out.println("======loop set membershipValue======");
//				System.out.println("membershipValue[" + i + "][" + j + "]: " + membershipValue[i][j]);
				result += membershipValue[i][j];
			}

//			System.out.println("total [" + i + "]: " + total);
//			System.out.println("result [" + i + "]: " + result);
		}

		return membershipValue;
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
				temp[i][j] = a / b;
				clusterCenters[i][j] = temp[i][j];

//				System.out.println("clusterCenter dari nilai[" + i + "][" + j + "]: " + clusterCenters[i][j]);
//				System.out.println("===================================");
			}
		}

		return clusterCenters;
	}

	private double objectiveFunction(int clusterNumber, int fuzziness, List<Lesson> lessons, int totalData,
			List<Student> students, double[][] matrix, double[][] clusterCenters, double[][] totalFO) {
		int totalAttributes = lessons.size();

		double[][] hitungFO = new double[totalData][clusterNumber];
		double fBaru = 0;

		for (int i = 0; i < totalData; i++) {
			for (int j = 0; j < clusterNumber; j++) {
				for (int k = 0; k < totalAttributes; k++) {
					int attr = k;
					List<Enroll> enrolls = new ArrayList<>();
					students.get(i).getStudentClassrooms().stream().forEach(p -> {
						enrolls.addAll(p.getEnrolls());
					});
					int score = enrolls.stream().filter(p -> p.getLesson().getId() == lessons.get(attr).getId())
							.mapToInt(Enroll::getScore).sum();
//					System.out.println("score: " + score + " clusterCenter: " + clusterCenters[j][k]);
					totalFO[i][j] += Math.pow((score - clusterCenters[j][k]), fuzziness);
				}
				hitungFO[i][j] = totalFO[i][j] * Math.pow(matrix[i][j], fuzziness);
//				System.out.println("nilai hitung sementara fungsi objektif[" + i + "][" + j + "]: " + hitungFO[i][j]);
				fBaru += hitungFO[i][j];
			}
		}
//		System.out.println("nilai fungsi objektif: " + fBaru);
		return fBaru;
	}

	private double[][] generateNewMatrixPartition(int clusterNumber, int fuzziness, List<Lesson> lessons, int totalData,
			double[][] totalFO) {
		double pow = -1 / (fuzziness - 1);
		double[] total = new double[totalData];
		double totalPow = 0;
		double[][] newMatrix = new double[totalData][clusterNumber];
		double result = 0;
		
		// set a
		for (int i = 0; i < totalData; i++) {
			for (int j = 0; j < clusterNumber; j++) {
				total[i] += totalFO[i][j];
			}
			totalPow += Math.pow(total[i], pow);
		}
		
		for (int i = 0; i < totalData; i++) {
			for (int j = 0; j < clusterNumber; j++) {
				newMatrix[i][j] = Math.pow(totalFO[i][j], pow) / totalPow;
				result += newMatrix[i][j];
//				System.out.println("newMatrix[" + i + "][" + j + "]: " + newMatrix[i][j]);
			}
			
//			System.out.println("result[" + i + "]" + result);
			result = 0;
		}
		
		return newMatrix;
	}
	
	private double checkConvergence(int clusterNumber, int totalData, double[][] matrix, double[][] newMatrix) {
		double sum = 0;
		for (int i = 0; i < totalData; i++) {
			for (int j = 0; j < clusterNumber; j++) {
				sum += Math.pow(newMatrix[i][j] - matrix[i][j], 2);
			}
		}
		return Math.sqrt(sum);
	}

}
