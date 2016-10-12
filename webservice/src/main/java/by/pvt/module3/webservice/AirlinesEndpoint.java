package by.pvt.module3.webservice;


import by.pvt.module3.entity.Airline;
import by.pvt.module3.webservice.common.CommonRepository;
import by.pvt.module3.webservice.schema.AirlineType;
import by.pvt.module3.webservice.schema.GetAirlineRequest;
import by.pvt.module3.webservice.schema.GetAirlineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by SBT-Dombrovsky-AE on 11.10.2016.
 */
@Endpoint
public class AirlinesEndpoint {
    private static final String NAMESPACE_URI = "http://modules3.pvt.by/airlines/webservice";

    @Autowired
    private CommonRepository<AirlineType, Airline> airlinesRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAirlineRequest")
    @ResponsePayload
    public GetAirlineResponse getAirline(@RequestPayload GetAirlineRequest request) {
        GetAirlineResponse response = new GetAirlineResponse();
        response.setAirline(airlinesRepository.getById(request.getId()));
        return response;
    }
}
