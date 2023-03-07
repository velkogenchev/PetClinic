package com.petclinic.bootstrap;

import com.petclinic.model.Owner;
import com.petclinic.model.Vet;
import com.petclinic.services.OwnerService;
import com.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Velko");
        owner1.setLastName("Genchev");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Hristo");
        owner2.setLastName("Genchev");

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Person");
        vet1.setLastName("One");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Person");
        vet2.setLastName("Two");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
