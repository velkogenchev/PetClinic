package com.petclinic.bootstrap;

import com.petclinic.model.*;
import com.petclinic.services.OwnerService;
import com.petclinic.services.PetTypeService;
import com.petclinic.services.SpecialityService;
import com.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedSpec1 = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSpec2 = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedSpec3 = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Velko");
        owner1.setLastName("Genchev");
        owner1.setAddress("123 Street");
        owner1.setCity("Plovdiv");
        owner1.setTelephone("01231235443");

        Pet velkosPet = new Pet();
        velkosPet.setPetType(savedDogPetType);
        velkosPet.setOwner(owner1);
        velkosPet.setBirthDate(LocalDate.now());
        velkosPet.setName("Rex");

        owner1.getPets().add(velkosPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Hristo");
        owner2.setLastName("Genchev");
        owner2.setAddress("123 Street");
        owner2.setCity("Plovdiv");
        owner2.setTelephone("01231235443");

        Pet hristoPet = new Pet();
        hristoPet.setPetType(savedCatPetType);
        hristoPet.setOwner(owner2);
        hristoPet.setBirthDate(LocalDate.now());
        hristoPet.setName("Jinx");

        owner2.getPets().add(hristoPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Person");
        vet1.setLastName("One");
        vet1.getSpecialties().add(savedSpec1);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Person");
        vet2.setLastName("Two");
        vet2.getSpecialties().add(savedSpec2);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
