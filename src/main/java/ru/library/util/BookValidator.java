package ru.library.util;

//@Component
//public class BookValidator implements Validator {
//    private final BookDAO bookDAO;
//    @Autowired
//    public BookValidator(BookDAO bookDAO) {
//        this.bookDAO = bookDAO;
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
