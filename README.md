# Cinema-Room-REST-Service

`Port: 28852`

Stage 1/4: The show begins
Description
There are many fun activities on the planet Earth, and one of them is going to the movies. It is arguably the most accessible, inclusive, and fulfilling entertainment. Bring your friends or loved ones — each movie is a whole new adventure waiting to be experienced.

Let's make our virtual movie theater with the help of a REST service. Our movie theater has 9 rows with 9 seats each. In this stage, you need to create a simple endpoint that will return the information about the cinema in JSON format.

Objectives
Implement the /seats endpoint that handles GET requests and returns the information about the movie theatre.

The response should contain information about the rows, columns, and available seats. The response is a JSON object and has the following format:

{
   "total_rows":5,
   "total_columns":6,
   "available_seats":[
      {
         "row":1,
         "column":1
      },

      ........

      {
         "row":5,
         "column":5
      },
      {
         "row":5,
         "column":6
      }
   ]
}
Our cinema room has 9 rows with 9 seats each, so the total number of respective rows and columns also amounts to 9 each.

Note that the available_seats array contains 81 elements, as there are 81 seats in the room.

Example
Example 1: a GET /seats request
Response body:

{
   "total_rows":9,
   "total_columns":9,
   "available_seats":[
      {
         "row":1,
         "column":1
      },
      {
         "row":1,
         "column":2
      },
      {
         "row":1,
         "column":3
      },

      ........

      {
         "row":9,
         "column":8
      },
      {
         "row":9,
         "column":9
      }
   ]
}
