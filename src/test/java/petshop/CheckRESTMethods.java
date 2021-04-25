package petshop;

import clients.petstore.PetStore;
import clients.petstore.PetStoreService;
import models.pet.Category;
import models.pet.Pet;
import models.pet.ResponseError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class CheckRESTMethods {
    private PetStore petStore;

    @BeforeEach
    public final void beforeEach() {
        petStore = new PetStoreService().getPetStore();
    }

    public static Stream<Arguments> param() {
        Pet cat = new Pet();
        cat.setId(12);
        cat.setName("Daniil");
        cat.setStatus("available");
        Category categoryCat = new Category();
        categoryCat.setName("Cat");
        cat.setCategory(categoryCat);

        Pet dog = new Pet();
        dog.setId(14);
        dog.setName("Boris");
        dog.setStatus("sold");
        Category categoryDog = new Category();
        categoryDog.setName("Dog");
        dog.setCategory(categoryDog);

        return Arrays.asList(cat, dog).stream().map(it -> Arguments.of(it));
    }

    @ParameterizedTest
    @MethodSource("param")
    public final void post200(final Pet myPet) throws IOException {
        Pet createdPet = petStore.createPet(myPet).execute().body();
        Assertions.assertEquals(myPet, createdPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public final void get200(final Pet myPet) throws IOException {
        Pet createdPet = petStore.createPet(myPet).execute().body();
        Pet resultPet = petStore.getPetById(createdPet.getId()).execute().body();
        Assertions.assertEquals(myPet, resultPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public final void get404(final Pet myPet) throws IOException {
        ResponseError responseError = new ResponseError();
        responseError.setCode(1);
        responseError.setType("error");
        responseError.setMessage("Pet not found");
        Pet createdPet = petStore.createPet(myPet).execute().body();
        petStore.deletePet(createdPet.getId()).execute();
        ResponseError resultPet = petStore.getDeletedPetById(createdPet.getId()).execute().body();
        Assertions.assertEquals(responseError, resultPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public final void put200(final Pet myPet) throws IOException {
        Pet createdPet = petStore.createPet(myPet).execute().body();
        createdPet.setName("Pushistik");
        Pet changedPet = petStore.changePet(createdPet).execute().body();
        Assertions.assertEquals(createdPet, changedPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public final void put404(final Pet myPet) throws IOException {
        Pet createdPet = petStore.createPet(myPet).execute().body();
        createdPet.setName("Pushistik");
        petStore.deletePet(createdPet.getId()).execute();
        Pet resultPet = petStore.changePet(createdPet).execute().body();
        Assertions.assertNull(resultPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public final void delete200(final Pet myPet) throws IOException {
        ResponseError responseDelete = new ResponseError();
        responseDelete.setCode(200);
        responseDelete.setType("unknown");
        responseDelete.setMessage(String.valueOf(myPet.getId()));
        Pet createdPet = petStore.createPet(myPet).execute().body();
        ResponseError answer = petStore.deletePet(createdPet.getId()).execute().body();
        Assertions.assertEquals(responseDelete, answer);
    }

    @ParameterizedTest
    @MethodSource("param")
    public final void delete404(final Pet myPet) throws IOException {
        Pet createdPet = petStore.createPet(myPet).execute().body();
        petStore.deletePet(createdPet.getId()).execute();
        ResponseError answer = petStore.deletePet(createdPet.getId()).execute().body();
        Assertions.assertNull(answer);
    }
}
