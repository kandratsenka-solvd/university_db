SELECT full_name, email, group_name, degree_name, title_name
  FROM degree
 INNER JOIN edu_group ON degree.degree_id = edu_group.degree_id
 INNER JOIN student ON edu_group.edu_group_id = student.edu_group_id
 INNER JOIN person ON student.person_id = person.person_id
 INNER JOIN title ON person.title_id = title.title_id
 WHERE student.student_id = (?);