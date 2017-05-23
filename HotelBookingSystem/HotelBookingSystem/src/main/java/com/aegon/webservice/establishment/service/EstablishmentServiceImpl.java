package com.aegon.webservice.establishment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aegon.webservice.establishment.api.EstablishmentService;
import com.aegon.webservice.establishment.model.Establishment;
import com.aegon.webservice.establishment.repository.EstablishmentRepository;

@Component("establishmentService")
@Transactional
public class EstablishmentServiceImpl implements EstablishmentService {
	
	@Autowired
	private EstablishmentRepository establishmentRepository;

	@Override
	public Establishment getEstablishment(long establishmentId) {
        return establishmentRepository.findByEstablishmentId(establishmentId);
    }
}
