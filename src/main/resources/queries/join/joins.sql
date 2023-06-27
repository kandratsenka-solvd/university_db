-- one big join statement

SELECT p.full_name, s.student_id, t.title_name 
  FROM person p
  JOIN student s ON p.person_id = s.person_id
  JOIN title t ON p.title_id = t.title_id
  JOIN graduate g ON p.person_id = g.person_id
  JOIN lecturer l ON p.person_id = l.person_id
  JOIN applicant a ON p.person_id = a.person_id
  JOIN qualification q ON q.qualification_id = g.qualification_id
  JOIN edu_group eg ON s.edu_group_id = eg.edu_group_id
  JOIN degree d ON g.degree_id = d.degree_id
  JOIN department dept ON eg.department_id = dept.department_id
  JOIN faculty f ON dept.faculty_id = f.faculty_id
  JOIN course c ON a.course_id = c.course_id
  JOIN subject_course sc ON c.course_id = sc.course_id
  JOIN subject subj ON sc.subject_id = subj.subject_id
  JOIN grades gr ON s.student_id = gr.student_id AND subj.subject_id = gr.subject_id;

-- left join:

SELECT *
  FROM person
  LEFT JOIN student ON person.person_id = student.person_id;

-- right join:

Copy code
SELECT *
  FROM person
 RIGHT JOIN student ON person.person_id = student.person_id;

-- inner join:

SELECT *
  FROM person
 INNER JOIN student ON person.person_id = student.person_id;

-- outer join:

SELECT *
  FROM person
  FULL OUTER JOIN student ON person.person_id = student.person_id;