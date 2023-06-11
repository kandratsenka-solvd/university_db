SELECT full_name, email, group_name, degree_name, status_name
FROM
degree INNER JOIN edu_group ON degree.degree_id = edu_group.degree_id
INNER JOIN student ON edu_group.group_id = student.group_id
INNER JOIN person ON student.person_id = person.person_id
INNER JOIN status ON person.status_id = status.status_id
WHERE student.student_id = (?);