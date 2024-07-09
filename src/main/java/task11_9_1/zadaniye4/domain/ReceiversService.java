package task11_9_1.zadaniye4.domain;

import retrofit2.Retrofit;
import task11_9_1.zadaniye4.data_sources.ReceiverApiDataSource;

public class ReceiversService {
    final ReceiverApiDataSource receiverApiDataSource;

    public ReceiversService(ReceiverApiDataSource receiverApiDataSource) {
        this.receiverApiDataSource = receiverApiDataSource;
    }
}
