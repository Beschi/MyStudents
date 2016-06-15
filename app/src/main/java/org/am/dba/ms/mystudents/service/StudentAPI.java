package org.am.dba.ms.mystudents.service;

import org.am.dba.ms.mystudents.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Beschi Antony D on 10/6/2016.
 */
public interface StudentAPI {
    @GET("students")
    Call<List<Student>> getStudents();
}
