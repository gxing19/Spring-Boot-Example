package com.gxitsky.service.impl;

/*
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> queryAll() {
        List<Person> cityList = personRepository.findAll();
        return cityList;
    }

    @Override
    public Person queryById(Long cityId) {
//        return cityRepository.findOne(cityId);            //springboot 1.5.x.Release
        return personRepository.findById(cityId).get();       //springboot 2.0.x.Release
    }
}
*/
