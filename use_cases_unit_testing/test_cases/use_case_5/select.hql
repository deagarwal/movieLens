select user_id, max(rating), min(rating), round(avg(rating),2) from ratings group by user_id;
