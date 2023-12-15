package cl.ucn.disc.as.conserjeria.services;

import cl.ucn.disc.as.conserjeria.grpc.PersonaGrpc;
import cl.ucn.disc.as.conserjeria.grpc.PersonaGrpcRequest;
import cl.ucn.disc.as.conserjeria.grpc.PersonaGrpcResponse;
import cl.ucn.disc.as.conserjeria.grpc.PersonaGrpcServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PersonaGrpcServiceImpl extends PersonaGrpcServiceGrpc.PersonaGrpcServiceImplBase{

    /**
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void retrieve(PersonaGrpcRequest request, StreamObserver<PersonaGrpcResponse> responseObserver) {
       log.debug("Retrieving PersonaGrpc with rut={}.", request.getRut());

       PersonaGrpc personaGrpc = PersonaGrpc.newBuilder()
               .setRut("231231232")
               .setNombre("Diego")
               .setApellidos("Urrutia Astorga")
               .setEmail("durrutia@ucn.cl")
               .setTelefono("+56 2 2355166")
               .build();

       responseObserver.onNext(PersonaGrpcResponse.newBuilder().setPersona(personaGrpc).build());

       responseObserver.onCompleted();
    }
}
