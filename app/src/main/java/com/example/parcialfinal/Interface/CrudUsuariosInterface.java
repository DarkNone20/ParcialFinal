package com.example.parcialfinal.Interface;


import com.example.parcialfinal.Model.Usuarios;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudUsuariosInterface {

    @GET("/consultarAll")
    Call<List<Usuarios>> getAll();

    @POST("/crear")
    Call<Usuarios> createUsuario(@Body RequestBody usuario);

    @GET("/actualizar/{id}")
    Call<Usuarios> getUsuarioById(@Path("id") Long id);

    @DELETE("/eliminarTodos")
    Call<Void> deleteAllUsuarios();

    @DELETE("/user/{id}")
    Call<Void> deleteUsuarioById(@Path("id") Long id);

    @PUT("actualizar/{id}")
    Call<Usuarios> updateUsuarioById(@Path("id") Long id, @Body RequestBody requestBody);

    @POST("login")
    Call<Usuarios> loginUsuario(@Body RequestBody user);
}
