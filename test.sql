SELECT i.name, COUNT(*) 
FROM instructor i INNER JOIN lecture l ON i.id = l.instructor_id
GROUP BY i.name