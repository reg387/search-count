The following are the steps to be done to be able to build, deploy and test the search count application.

1. Download the search-count source codes from the repository

2. Build the search-count project using maven.

3. Run the application by executing the main SearchCountApplication (Spring boot). Current port is 8080, changes can be made in application.properties.

Problems:

1: Search the following texts, which will return the counts respectively.

Request: $ curl http://localhost:8080/counter-api/search -H "Authorization: Basic aW50ZWxpbWVudDppbnRlbGltZW50" -d’{“searchText”:[“Duis”, “Sed”, “Donec”, “Augue”, “Pellentesque”, “123”]}’ -H "Content-Type:application/json" -X POST

Response: {"counts": [{"Duis": 11}, {"Sed": 16}, {"Donec": 8}, {"Augue": 7}, {"Pellentesque": 6},{"123": 0}]}

2: Task 2: Provide the top 20 (as Path Variable) Texts, which has the highest counts in the Sample Paragraphs provided.

Request: curl curl http://localhost:8080/counter-api/top/10 -H "Authorization: Basic aW50ZWxpbWVudDppbnRlbGltZW50" -H "Accept:text/csv"

Response: eget|17,vel|17,sed|16,in|15,et|14,eu|13,ut|13,ac|12,amet|12,id|12,
