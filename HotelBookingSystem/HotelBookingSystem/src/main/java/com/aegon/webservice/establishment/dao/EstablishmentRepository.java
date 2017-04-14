package com.aegon.webservice.establishment.dao;

import org.springframework.data.repository.Repository;

import com.aegon.webservice.establishment.model.Establishment;

public interface EstablishmentRepository extends Repository<Establishment, Long>{
	Establishment findByEstablishmentId( long establishmentId );
}
