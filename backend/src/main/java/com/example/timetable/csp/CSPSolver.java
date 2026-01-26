package com.example.timetable.csp;

import com.example.timetable.model.Subject;
import java.util.*;

public class CSPSolver {

    static final String[] DAYS = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
    static final int PERIODS = 8;

    List<Subject> subjects;
    String[][] table = new String[5][PERIODS];

    Map<String,Integer> weeklyCount = new HashMap<>();
    Map<String,int[]> dailyCount = new HashMap<>();

    List<Map<String,String[]>> solutions = new ArrayList<>();

    public CSPSolver(List<Subject> subjects) {
        this.subjects = subjects;
        for (Subject s : subjects) {
            weeklyCount.put(s.name, 0);
            dailyCount.put(s.name, new int[5]);
        }
    }

    public List<Map<String,String[]>> solveAll() {
        backtrack(0,0);
        return solutions;
    }

    void backtrack(int day, int period) {
        if (day == 5) {
            Map<String,String[]> sol = new LinkedHashMap<>();
            for (int d=0; d<5; d++)
                sol.put(DAYS[d], table[d].clone());
            solutions.add(sol);
            return;
        }

        int nd = day, np = period+1;
        if (np == PERIODS) { nd++; np = 0; }

        for (Subject s : subjects) {
            if (!valid(s, day, period)) continue;

            if (s.isLab) {
                if (period > 4 || !free(day,period,4)) continue;
                placeLab(s, day, period);
                backtrack(nd,np);
                removeLab(s, day, period);
            } else {
                table[day][period] = s.name;
                weeklyCount.put(s.name, weeklyCount.get(s.name)+1);
                dailyCount.get(s.name)[day]++;
                backtrack(nd,np);
                table[day][period] = null;
                weeklyCount.put(s.name, weeklyCount.get(s.name)-1);
                dailyCount.get(s.name)[day]--;
            }
        }
    }

    boolean valid(Subject s, int day, int period) {
        if (weeklyCount.get(s.name) >= s.weekly) return false;

        if (!s.isLab) {
            if (dailyCount.get(s.name)[day] >= 2) return false;
            if (period>1 &&
                s.name.equals(table[day][period-1]) &&
                s.name.equals(table[day][period-2]))
                return false;
        }
        return true;
    }

    boolean free(int d,int p,int len){
        for(int i=0;i<len;i++)
            if(table[d][p+i]!=null) return false;
        return true;
    }

    void placeLab(Subject s,int d,int p){
        for(int i=0;i<4;i++) table[d][p+i]=s.name;
        weeklyCount.put(s.name, weeklyCount.get(s.name)+4);
    }

    void removeLab(Subject s,int d,int p){
        for(int i=0;i<4;i++) table[d][p+i]=null;
        weeklyCount.put(s.name, weeklyCount.get(s.name)-4);
    }
}