package com.tech.ditraktir.network;

import com.tech.ditraktir.model.RetroPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mfstech on 30/08/18.
 */

public interface GetDataService {

    @GET("getProject.php")
    Call<List<RetroPhoto>> getAllPhotos();
}