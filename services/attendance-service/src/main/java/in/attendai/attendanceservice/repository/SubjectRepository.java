package in.attendai.attendanceservice.repository;

import in.attendai.attendanceservice.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long>
{
    boolean existsBySubjectCode(String subjectCode);

    Optional<Subject> findBySubjectCode(String subjectCode);

    List<Subject> findByFacultyId(Long facultyId);

    List<Subject> findByDepartmentAndSemester(
            String department,
            Integer semester
    );

    List<Subject> findByActiveTrue();

    List<Subject> findByFacultyIdAndActiveTrue(Long facultyId);
}
