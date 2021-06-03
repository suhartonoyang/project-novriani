package com.project.novriani.service.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.novriani.bean.Centroid;
import com.project.novriani.bean.Cluster;
import com.project.novriani.bean.Record;
import com.project.novriani.model.Enroll;
import com.project.novriani.model.Student;
import com.project.novriani.model.StudentClassroom;
import com.project.novriani.service.StudentService;
import com.project.novriani.service.UserService;
import com.project.novriani.utils.Distance;

@Service
public class KMeansService {

	@Autowired
	private UserService userService;

	@Autowired
	private StudentService studentService;

	/**
	 * Performs the K-Means clustering algorithm on the given dataset.
	 *
	 * @param records       The dataset.
	 * @param k             Number of Clusters.
	 * @param distance      To calculate the distance between two items.
	 * @param maxIterations Upper bound for the number of iterations.
	 * @return K clusters along with their features.
	 */
	public List<Cluster> run(int totalClusters, Distance distance, int maxIterations) {
		List<Record> data = generateData();
		Map<Centroid, List<Record>> clusters = KMeans.fit(data, totalClusters, new EuclideanDistance(), maxIterations);
		List<Cluster> clustersList = new ArrayList<>();
		AtomicInteger clusterNumber = new AtomicInteger(1);
		clusters.entrySet().forEach(p -> {
			Cluster cluster = new Cluster();
			cluster.setCentroid(p.getKey());
			cluster.setRecords(p.getValue());
			cluster.setClusterNumber(clusterNumber.getAndIncrement());
			clustersList.add(cluster);
		});
		return clustersList;
	}

	private List<Record> generateData() {
		List<Student> students = studentService.getStudentAll();
		List<Record> data = new ArrayList<>();
		for (Student student : students) {
			long studentId = student.getId();
			String studentName = student.getStudentName();
			Set<StudentClassroom> studentClassrooms = student.getStudentClassrooms();
			Set<Enroll> enrolls = studentClassrooms.stream().flatMap(m -> m.getEnrolls().stream())
					.collect(Collectors.toSet());
			Map<String, Double> features = new HashMap<>();
			for (Enroll enroll : enrolls) {
				features.put(enroll.getLesson().getLessonName(), (double) enroll.getScore());
			}

			if (!features.isEmpty()) {
				Record record = new Record(studentId, studentName, features);
				data.add(record);
			}
		}

//		data.forEach(p -> {
//			System.out.println("student: " + p.getStudentId() + " - " + p.getStudentName());
//			p.getFeatures().entrySet().forEach(a -> {
//				System.out.println("features: " + a.getKey() + " - " + a.getValue());
//			});
//		});

		return data;
	}

	public static Centroid sortedCentroid(Centroid key) {
		List<Map.Entry<String, Double>> entries = new ArrayList<>(key.getCoordinates().entrySet());
		entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

		Map<String, Double> sorted = new LinkedHashMap<>();
		for (Map.Entry<String, Double> entry : entries) {
			sorted.put(entry.getKey(), entry.getValue());
		}

		return new Centroid(sorted);
	}

}
