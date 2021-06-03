package com.project.novriani.service.cmeans;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.novriani.model.Enroll;
import com.project.novriani.model.Lesson;
import com.project.novriani.model.Student;
import com.project.novriani.model.StudentClassroom;
import com.project.novriani.service.StudentService;

@Service
public class CMeansService1 {

	@Autowired
	private StudentService studentService;

	public void run() {
		int clusterNumber = 6;
		int maxIteration = 100;
		int fuzziness = 2;
		double epsilon = 0.01;

		List<Student> students = studentService.getStudentAll();
		int totalData = (int) students.stream().filter(p -> {
			return p.getStudentClassrooms().stream().filter(q -> {
				return !q.getEnrolls().isEmpty();
			}).findAny().isPresent();
		}).count();
		
		totalData = 10;

		double[][] membershipValue = generateRandomMatrixPartition(clusterNumber, totalData);
		calcClusterCenter(fuzziness, clusterNumber, totalData, students, membershipValue);
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

			for (int j = 0; j < clusterNumber; j++) {
				membershipValue[i][j] /= total;
//				System.out.println("======loop set membershipValue======");
				System.out.println("membershipValue[" + i + "][" + j + "]: " + membershipValue[i][j]);
				result += membershipValue[i][j];
			}

//			System.out.println("total [" + i + "]: " + total);
			System.out.println("result [" + i + "]: " + result);
		}

		return membershipValue;
	}

	private double[][] calcClusterCenter(double powW, int clusterNumber, int totalData, List<Student> students,
			double[][] membershipValue) {
		int row = 10;
		int column = 10;
		double clusterCenters[][] = new double[column][clusterNumber];
		double rankValue;
		double multiplyValue;
		double sumX[][] = new double[row][clusterNumber];

		for (int i = 0; i < clusterNumber; i++) {
			System.out.println("cluster " + i);
			for (int k = 0; k < column; k++) {
				rankValue = 0;
				multiplyValue = 0;
				List<Enroll> enrolls = new ArrayList<Enroll>();
				students.get(k).getStudentClassrooms().stream().forEach(p -> {
					enrolls.addAll(p.getEnrolls());
				});
				List<Enroll> enrollsSorted = enrolls.stream().sorted(Comparator.comparing(Enroll::getId))
						.collect(Collectors.toList());

				for (int j = 0; j < row; j++) {
					if (k == 0) {
						System.out.println(enrollsSorted.get(k).toString());
						multiplyValue += Math.pow(membershipValue[j][i], powW) * enrolls.get(j).getScore();
					}

					rankValue += Math.pow(membershipValue[j][i], powW);
					sumX[j][k] = multiplyValue / rankValue;
					clusterCenters[k][i] = sumX[j][k];
				}
				System.out.println("clusterCenter dari nilai[" + i + "][" + k + "]: " + clusterCenters[k][i]);
				System.out.println("===================================");
			}
		}

		return null;
	}
}
