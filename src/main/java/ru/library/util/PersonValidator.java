package ru.library.util;

//@Component
//public class PersonValidator implements Validator {
//    private final PersonDAO personDAO;
//    @Autowired
//    public PersonValidator(PersonDAO personDAO) {
//        this.personDAO = personDAO;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Person.class.equals(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        Person person = (Person) target;
////        if (personDAO.show(person.getEmail()).isPresent()){
////            errors.rejectValue("email", "", "this email is already taken");
////        }
//
//    }
//}
