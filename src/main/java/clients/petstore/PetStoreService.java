package clients.petstore;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PetStoreService {
    private PetStore petStore;
    public static final String BASE_URL = "https://petstore.swagger.io/";

    public final PetStore getPetStore() {
        if (petStore == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl(BASE_URL).build();
            petStore = retrofit.create(PetStore.class);
            return petStore;
        } else {
            return petStore;
        }
    }
}
