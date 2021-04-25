package clients.petstore;

import models.pet.Pet;
import models.pet.ResponseError;
import retrofit2.Call;
import retrofit2.http.*;

public interface PetStore {

    @POST("/v2/pet")
    Call<Pet> createPet(@Body Pet pet);

    @GET("/v2/pet/{id}")
    Call<Pet> getPetById(@Path("id") int id);

    @GET("/v2/pet/{id}")
    Call<ResponseError> getDeletedPetById(@Path("id") int id);

    @DELETE("/v2/pet/{id}")
    Call<ResponseError> deletePet(@Path("id") int id);

    @PUT("/v2/pet")
    Call<Pet> changePet(@Body Pet pet);
}
