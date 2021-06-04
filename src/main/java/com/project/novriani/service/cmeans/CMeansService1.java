package com.project.novriani.service.cmeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.novriani.bean.ClusterCenter;
import com.project.novriani.bean.ClusterCenter2D;
import com.project.novriani.bean.Matrix;
import com.project.novriani.bean.Matrix2D;
import com.project.novriani.bean.Response;
import com.project.novriani.model.Enroll;
import com.project.novriani.model.Lesson;
import com.project.novriani.model.Student;
import com.project.novriani.service.LessonService;
import com.project.novriani.service.StudentService;

@Service
public class CMeansService1 {

	@Autowired
	private StudentService studentService;

	@Autowired
	private LessonService lessonService;

	public List<Matrix> run(int clusterNumber, int maxIteration, int fuzziness, double epsilon) {
//		int clusterNumber = 5;
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
//		totalData = 10;

		List<Lesson> lessons = lessonService.getLessonAll();

		List<Matrix> totalFO = new ArrayList<Matrix>();
		List<Matrix> matrixes = generateRandomMatrixPartition(clusterNumber, totalData, students);
		List<Matrix> newMatrixes = new ArrayList<Matrix>();
		double fLama = 0;

		for (int i = 0; i < maxIteration; i++) {
			List<ClusterCenter> clusterCenters = calcClusterCenter(clusterNumber, fuzziness, lessons, totalData,
					students, matrixes);
			double fBaru = objectiveFunction(clusterNumber, fuzziness, lessons, totalData, students, matrixes,
					clusterCenters, totalFO);
			newMatrixes = generateNewMatrixPartition(clusterNumber, fuzziness, lessons, totalData, matrixes, totalFO);
			matrixes = newMatrixes;
			
			if (i == 0) {
				fLama = fBaru;
			} else {
				double selisih = fBaru - fLama;
				if (selisih < epsilon)
					break;
				else
					fLama = fBaru;
			}
//			matrixes = newMatrixes;
		}

		List<Matrix> finalResults = new ArrayList<Matrix>();
		matrixes.stream().forEach(p -> {
//			System.out.println(p);
			List<Matrix2D> sortedColumns = new ArrayList<Matrix2D>();
			sortedColumns.addAll(p.getColumns());
			sortedColumns.sort(Comparator.comparing(Matrix2D::getValue).reversed());

			Matrix m = new Matrix(p.getStudentId(), p.getStudentName(), p.getRow(),
					Collections.singletonList(sortedColumns.get(0)));

			finalResults.add(m);
		});
		
//		finalResults.stream().forEach(p->{
//			System.out.println(p);
//		});
		
		return finalResults;
	}

	private List<Matrix> generateRandomMatrixPartition(int clusterNumber, int totalData, List<Student> students) {
		List<Matrix> matrixes = new ArrayList<Matrix>();
		Random random = new Random();
		for (int i = 0; i < totalData; i++) {
			float total = 0;
			double result = 0;
			List<Matrix2D> columns = new ArrayList<Matrix2D>();
			for (int j = 0; j < clusterNumber; j++) {
				Matrix2D column = new Matrix2D(j, j, Math.round(random.nextDouble() * 100.0));
				columns.add(column);
				total += column.getValue();
			}

			for (Matrix2D column : columns) {
				double value = column.getValue();
				column.setValue(value / total);
//				System.out.println("matrix[" + i + "][" + column.getColumn() + "]: " + column.getValue());
				result += column.getValue();
			}

			Matrix matrix = new Matrix();
			matrix.setStudentId(students.get(i).getId());
			matrix.setStudentName(students.get(i).getStudentName());
			matrix.setRow(i);
			matrix.setColumns(columns);

			matrixes.add(matrix);

//			System.out.println("total [" + i + "]: " + total);
//			System.out.println("result [" + i + "]: " + result);
		}
		
//		System.out.println("Matrix");
//		matrixes.stream().forEach(p->{
//			System.out.println(p);
//		});

		return matrixes;

//		return matrix;
	}

	private List<ClusterCenter> calcClusterCenter(int clusterNumber, int fuzziness, List<Lesson> lessons, int totalData,
			List<Student> students, List<Matrix> matrixes) {
		int totalAttributes = lessons.size();
		List<ClusterCenter> clusterCenters = new ArrayList<ClusterCenter>();

		for (int i = 0; i < clusterNumber; i++) {
			int rowCluster = i;
			List<ClusterCenter2D> columns = new ArrayList<ClusterCenter2D>();
			for (int j = 0; j < totalAttributes; j++) {
				int attr = j;
				double a = 0;
				double b = 0;
				for (int k = 0; k < totalData; k++) {
					int rowMatrix = k;
					int columnMatrix = i;
					double valueMatrix = 0;
					List<Enroll> enrolls = new ArrayList<>();
					List<Matrix2D> columnsMatrix = new ArrayList<Matrix2D>();
					matrixes.stream().filter(p -> p.getRow() == rowMatrix).forEach(q -> {
						columnsMatrix.addAll(q.getColumns());
						students.stream().filter(r -> r.getId() == q.getStudentId()).forEach(s -> {
//							System.out.println(s.toString());
							s.getStudentClassrooms().stream().forEach(t -> {
								enrolls.addAll(t.getEnrolls());
							});
						});
						;
					});

					valueMatrix = columnsMatrix.stream().filter(p -> p.getColumn() == columnMatrix)
							.mapToDouble(Matrix2D::getValue).sum();
					int score = enrolls.stream().filter(p -> p.getLesson().getId() == lessons.get(attr).getId())
							.mapToInt(Enroll::getScore).sum();

//					System.out.println("Lesson id-desc: " + lessons.get(attr).getId() + "-"
//							+ lessons.get(attr).getLessonName() + ", Score: " + score);
//					System.out.println("matrix[" + rowMatrix + "][" + columnMatrix + "]: " + valueMatrix);

					b += Math.pow(valueMatrix, fuzziness) * 1;
					a += (b * score);
				}

				double value = a / b;

				ClusterCenter2D column = new ClusterCenter2D(lessons.get(attr).getId(),
						lessons.get(attr).getLessonName(), attr, value);
				columns.add(column);
			}

			ClusterCenter clusterCenter = new ClusterCenter(rowCluster, rowCluster, columns);
			clusterCenters.add(clusterCenter);
		}

//		clusterCenters.stream().forEach(r -> {
//			r.getColumns().stream().forEach(c -> {
//				System.out.println("Lesson: " + c.getLessonName());
//				System.out.println(
//						"clusterCenter dari nilai[" + r.getRow() + "][" + c.getColumn() + "]: " + c.getValue());
//			});
//		});

		return clusterCenters;
	}

	private double objectiveFunction(int clusterNumber, int fuzziness, List<Lesson> lessons, int totalData,
			List<Student> students, List<Matrix> matrixes, List<ClusterCenter> clusterCenters, List<Matrix> totalFO) {
		int totalAttributes = lessons.size();
		double fBaru = 0;

		for (int i = 0; i < totalData; i++) {
			int rowMatrix = i;
			List<Matrix2D> columnsMatrix = new ArrayList<Matrix2D>();
			List<Enroll> enrolls = new ArrayList<>();

			Matrix totalFORow = new Matrix();
			List<Matrix2D> totalFOColumns = new ArrayList<Matrix2D>();
			totalFORow.setRow(rowMatrix);
			matrixes.stream().filter(p -> p.getRow() == rowMatrix).forEach(q -> {
				totalFORow.setStudentId(q.getStudentId());
				totalFORow.setStudentName(q.getStudentName());

				columnsMatrix.addAll(q.getColumns());
				students.stream().filter(r -> r.getId() == q.getStudentId()).forEach(s -> {
					s.getStudentClassrooms().stream().forEach(t -> {
						enrolls.addAll(t.getEnrolls());
					});
				});
				;
			});

			for (int j = 0; j < clusterNumber; j++) {
				int columnMatrix = j;
				int rowClusterCenter = j;
				double valueTotalFO = 0;
				for (int k = 0; k < totalAttributes; k++) {
					int attr = k;
					int score = enrolls.stream().filter(p -> p.getLesson().getId() == lessons.get(attr).getId())
							.mapToInt(Enroll::getScore).sum();

					double valueClusterCenter = clusterCenters.stream().filter(p -> p.getRow() == rowClusterCenter)
							.flatMap(q -> q.getColumns().stream()
									.filter(r -> r.getLessonId() == lessons.get(attr).getId() && r.getColumn() == attr))
							.mapToDouble(ClusterCenter2D::getValue).sum();
//					System.out.println("score: " + score + " clusterCenter[" + rowClusterCenter + "][" + attr + "]: "
//							+ valueClusterCenter);
					valueTotalFO += Math.pow((score - valueClusterCenter), fuzziness);
				}

//				System.out.println("valueTotalFO[" + rowMatrix + "][" + columnMatrix + "]: " + valueTotalFO);

				Matrix2D totalFOColumn = new Matrix2D(columnMatrix, columnMatrix, valueTotalFO);
				totalFOColumns.add(totalFOColumn);
				totalFORow.setColumns(totalFOColumns);

				double valueMatrix = columnsMatrix.stream().filter(p -> p.getColumn() == columnMatrix)
						.mapToDouble(Matrix2D::getValue).sum();
//				System.out.println("matrix[" + rowMatrix + "][" + columnMatrix + "]: " + valueMatrix);
				double hitungFO = valueTotalFO * Math.pow(valueMatrix, fuzziness);
//				System.out.println("nilai hitung sementara fungsi objektif[" + i + "][" + j + "]: " + hitungFO);
				fBaru += hitungFO;

			}

			totalFO.add(totalFORow);
		}

//		totalFO.stream().forEach(r -> {
//			r.getColumns().stream().forEach(c -> {
//				System.out.println("totalFO dari nilai[" + r.getRow() + "][" + c.getColumn() + "]: " + c.getValue());
//			});
//		});

//		System.out.println("nilai fungsi objektif: " + fBaru);
		return fBaru;
	}

	private List<Matrix> generateNewMatrixPartition(int clusterNumber, int fuzziness, List<Lesson> lessons,
			int totalData, List<Matrix> matrixes, List<Matrix> totalFO) {
		double pow = -1 / (fuzziness - 1);
		double totalPow[] = new double[totalData];
		List<Matrix> newMatrixes = new ArrayList<Matrix>();
		double result = 0;

		// set a
		for (int i = 0; i < totalData; i++) {
			int row = i;
			double sumValueTotalFO = totalFO.stream().filter(p -> p.getRow() == row)
					.flatMap(q -> q.getColumns().stream()).mapToDouble(Matrix2D::getValue).sum();
//			System.out.println("totalFO[" + row + "]: " + sumValueTotalFO);
			totalPow[i] += Math.pow(sumValueTotalFO, pow);
		}

		for (int i = 0; i < totalData; i++) {
			int row = i;
			List<Matrix2D> columns = new ArrayList<Matrix2D>();
			for (int j = 0; j < clusterNumber; j++) {
				int col = j;
				double valueTotalFO = totalFO.stream().filter(p -> p.getRow() == row)
						.flatMap(q -> q.getColumns().stream().filter(r -> r.getColumn() == col))
						.mapToDouble(Matrix2D::getValue).sum();
				double value = Math.pow(valueTotalFO, pow) / totalPow[i];

				Matrix2D column = new Matrix2D(col, col, value);
				columns.add(column);
				result += valueTotalFO;
//				System.out.println("newMatrix[" + i + "][" + j + "]: " + value);
			}

			Matrix newMatrix = new Matrix();
			matrixes.stream().filter(p -> p.getRow() == row).forEach(q -> {
				newMatrix.setStudentId(q.getStudentId());
				newMatrix.setStudentName(q.getStudentName());
				newMatrix.setRow(row);
			});

			newMatrix.setColumns(columns);
			newMatrixes.add(newMatrix);
//			System.out.println("result[" + i + "]" + result);
			result = 0;
		}
//		System.out.println("NEW Matrix");
//		newMatrixes.stream().forEach(r -> {
//			System.out.println(r);
////			r.getColumns().stream().forEach(c -> {
////				System.out
////						.println("newMatrixes dari nilai[" + r.getRow() + "][" + c.getColumn() + "]: " + c.getValue());
////			});
//		});
		return newMatrixes;
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
				double matrix = matrixes.stream().filter(p -> p.getRow() == row)
						.flatMap(q -> q.getColumns().stream().filter(r -> r.getColumn() == col))
						.mapToDouble(Matrix2D::getValue).sum();

				sum += Math.pow(valueNewMatrix - matrix, fuzziness);
			}
		}
		return Math.sqrt(sum);
	}

}
