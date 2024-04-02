package com.cars.comparison.serivces.cars;

import com.cars.comparison.entity.Car;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class CarSimilarityCalculator {

    public List<Car> findSimilarCars(List<Car> allCars, Car specificCar, int limit) {
        List<SimilarityScore> similarityScores = new ArrayList<>();

        for (Car car : allCars) {
            double similarityScore = calculatePearsonCorrelation(specificCar, car);
            similarityScores.add(new SimilarityScore(car, similarityScore));
        }

        // Sort cars by similarity score
        similarityScores.sort(Comparator.comparingDouble(SimilarityScore::getSimilarityScore).reversed());

        // Return top N similar cars
        List<Car> similarCars = new ArrayList<>();
        for (int i = 0; i < Math.min(limit, similarityScores.size()); i++) {
            similarCars.add(similarityScores.get(i).getCar());
        }
        return similarCars;
    }

    private double calculatePearsonCorrelation(Car car1, Car car2) {
        PearsonsCorrelation correlation = new PearsonsCorrelation();
        double[] attributes1 = {car1.getSpecifications().getMaxTorque(), car1.getSpecifications().getMaxPower(), car1.getPrice()};
        double[] attributes2 = {car2.getSpecifications().getMaxTorque(), car2.getSpecifications().getMaxPower(), car2.getPrice()};
        return correlation.correlation(attributes1, attributes2);
    }

    private static class SimilarityScore {
        private Car car;
        private double similarityScore;

        public SimilarityScore(Car car, double similarityScore) {
            this.car = car;
            this.similarityScore = similarityScore;
        }

        public Car getCar() {
            return car;
        }

        public double getSimilarityScore() {
            return similarityScore;
        }
    }
}
