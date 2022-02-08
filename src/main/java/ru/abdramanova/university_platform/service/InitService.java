package ru.abdramanova.university_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.abdramanova.university_platform.entity.*;
import ru.abdramanova.university_platform.repositories.*;

import java.time.LocalDateTime;
import java.time.Month;


@Service
public class InitService {

    private final AssessmentRepository assessmentRepository;
    private final MaterialRepository materialRepository;
    private final PersonRepository personRepository;
    private final PersonRoleRepository personRoleRepository;
    private final StudyGroupRepository studyGroupRepository;
    private final SubInGroupRepository subInGroupRepository;
    private final SubjectRepository subjectRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public InitService(AssessmentRepository assessmentRepository, ControlFormDictRepository controlFormDictRepository, MaterialRepository materialRepository, PersonRepository personRepository, PersonRoleRepository personRoleRepository, StudyGroupRepository studyGroupRepository, SubInGroupRepository subInGroupRepository, SubjectRepository subjectRepository, TaskRepository taskRepository) {
        this.assessmentRepository = assessmentRepository;
        this.materialRepository = materialRepository;
        this.personRepository = personRepository;
        this.personRoleRepository = personRoleRepository;
        this.studyGroupRepository = studyGroupRepository;
        this.subInGroupRepository = subInGroupRepository;
        this.subjectRepository = subjectRepository;
        this.taskRepository = taskRepository;
    }

    public void initDB(){

        ControlFormDict test = new ControlFormDict("зачет");
        ControlFormDict exam = new ControlFormDict("экзамен");

        Subject combinatorics = new Subject("Комбинаторика", exam);
        Subject english = new Subject("Английский", test);
        Subject quantum = new Subject("Квантовая информатика", test);
        test.getSubjects().add(english);
        test.getSubjects().add(quantum);

        PersonRole teacher = new PersonRole("Преподаватель");
        PersonRole student = new PersonRole("Студент");
        PersonRole admin = new PersonRole("Администратор");

        StudyGroup mpi1 = new StudyGroup("МПИ-21-1-7");
        StudyGroup mvt2 = new StudyGroup("МИВТ-21-2-4");

        Person person1 = new Person("Абдраманова","Яна", "Расимовна", "89628569064", "Abdramanova.yana@yandex.ru", mpi1, student);
        Person person2 = new Person("Новикова", "Полина", "Сергеевна", "89876543211", "PS1234@yandex.ru", mvt2, student);
        Person person3 = new Person("Битаров", "Костантин","Эльбрусович", "891112223344", "TheNoneMan@gmai.com", mpi1, student);
        Person person4 = new Person("Бирина", "Венера", "Юрьевна", "89998887766", "BirinaV@mail.ru", teacher);

        SubInGroup combInMpi1 = new SubInGroup(LocalDateTime.of(2022, Month.JUNE, 23, 23, 59), combinatorics, mpi1, person4);

        Task task1 = new Task(new TaskKey(1, "Задача 1"), "Решить задачи A - F контеста 1",
                LocalDateTime.of(2022, Month.MARCH, 11, 23, 59), combInMpi1);

        Assessment assessment1 = new Assessment(56, person1, task1);
        Assessment assessment2 = new Assessment(71, person2, task1);

        subjectRepository.saveAndFlush(english);
        subjectRepository.saveAndFlush(quantum);

        personRoleRepository.save(teacher);
        personRoleRepository.save(student);
        personRoleRepository.save(admin);

        studyGroupRepository.save(mpi1);
        studyGroupRepository.save(mvt2);

        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);
        personRepository.save(person4);

        subInGroupRepository.save(combInMpi1);

        taskRepository.save(task1);

        assessmentRepository.save(assessment1);
        assessmentRepository.save(assessment2);
    }
}
