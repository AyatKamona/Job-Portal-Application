package com.team6.quickcashteam6;

import java.util.ArrayList;

public class RecommendationService {

    private static ArrayList<Employee> recommendedEmployees;

    static ArrayList<Employee> recommendation(ArrayList<String> skills, ArrayList<Employee> employees) {
        recommendedEmployees = new ArrayList<>();
        for (Employee employee: employees){
            int count=0;
            for (String skill : skills){

                if (employee.getSkills().contains(skill)){
                    count++;
                }
            }
            if (count>=2){
                recommendedEmployees.add(employee);
            }

        }
        return recommendedEmployees;
    }
}
