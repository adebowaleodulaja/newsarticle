"# newsarticle" 

**News Article API Docummentation**

**Signup Author**
----
  Returns json data about a newly created author.

* **URL**

  /api/v1/newsletter/author

* **Method:**

  `POST`
  
*  **URL Params**

   **None**
 

* **Data Params**

  None

* **Success Response:**

  * **Code:** 201 <br />
    **Content:** 
               
                  
                    {
                      "@id": "0b4eb76c-4dad-4d2d-99f8-6da0ac3ebc9e",
                      "authorID": 28,
                      "name": "Thomas",
                      "email": "to@yahoo.com",
                      "vpassword": "$2a$10$0akKKzuQwVmLJ6jshoH08uEV.1lp4axZ27jTCLOWnWTDV8sfiiD0a",
                      "phoneNumber": "234701234567",
                      "maritalStatus": "Divorsed",
                      "dateOfBirth": "02-11-210"
                       }
                    
 
* **Error Response:**

  * **Code:** 501 INTERNAL SERVER ERROR <br />
    **Content:** `{ error : "Internal Server Error" }`
    
* **Sample Call:**

  ```
  {
     	"name":"Thomas",
     	"email": "to@yahoo.com",
     	"vpassword": "password",
     	"phoneNumber": "234701234567",
     	"maritalStatus": "Divorsed",
         "dateOfBirth": "02-11-210"
     }
  ```
  
  
  **Get All Author**
  ----
    Returns json list of Authors.
  
  * **URL**
  
    /api/v1/newsletter/authors
  
  * **Method:**
  
    `GET`
    
  *  **URL Params**
  
     **None**
   
  
  * **Data Params**
  
    None
  
  * **Success Response:**
  
    * **Code:** 200 <br />
      **Content:** 
                 
                    
                      [
                          {
                              "@id": "7294e54c-a09b-44e7-84fd-d6aa9dcfec7e",
                              "authorID": 25,
                              "name": "Oladele",
                              "email": "oa@yahoo.com",
                              "vpassword": "$2a$10$jbiFaEmPAHoOBs/PzTUvRugU6TLE2voob38/4qCw3TStf1Vt4LRIm",
                              "phoneNumber": "234701234567",
                              "maritalStatus": "Single",
                              "dateOfBirth": "02-11-2000",
                              "articles": [
                                  {
                                      "id": 26,
                                      "title": "my third article",
                                      "content": "programming in Java is challenging which makes it better!",
                                      "published": 1,
                                      "author": "7294e54c-a09b-44e7-84fd-d6aa9dcfec7e"
                                  },
                                  {
                                      "id": 27,
                                      "title": "my second article",
                                      "content": "programming in Java is awesome!",
                                      "published": 0,
                                      "author": "7294e54c-a09b-44e7-84fd-d6aa9dcfec7e"
                                  }
                              ]
                          },
                          {
                              "@id": "a1fd1e8d-0214-4290-ad00-665f6c8c0da4",
                              "authorID": 28,
                              "name": "Thomas",
                              "email": "to@yahoo.com",
                              "vpassword": "$2a$10$0akKKzuQwVmLJ6jshoH08uEV.1lp4axZ27jTCLOWnWTDV8sfiiD0a",
                              "phoneNumber": "234701234567",
                              "maritalStatus": "Divorsed",
                              "dateOfBirth": "02-11-210",
                              "articles": []
                          }
                      ]
                      
   
  * **Error Response:**
  
    * **Code:** 501 INTERNAL SERVER ERROR <br />
      **Content:** `{ error : "Internal Server Error" }`
      
  * **Sample Call:**
  
    N/A
    

**Authentication**
----
  Returns a Java Web Token in JSON/XML format after which an author must have been successfully authenticated

* **URL**

  api/v1/newsletter/author/authenticate

* **Method:**

  `POST`
  
*  **URL Params**

   **None**
 

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
               
                  
                    {
                        "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b0B5YWhvby5jb20iLCJleHAiOjE1ODEyMjY3MzgsImlhdCI6MTU4MTE5MDczOH0.ldHLkK5-VdMHWOM9AsefDHUr_w3wsI4WXC7saHAFM9E"
                    }
                    
 
* **Error Response:**

  * **Code:** 501 INTERNAL SERVER ERROR <br />
    **Content:** `{ error : "Internal Server Error" }`
    
* **Sample Call:**

  ```
  {
  	"email": "to@yahoo.com",
  	"vpassword": "password"
  }
  ```
  

**Submit Article**
----
  Returns json data about a newly created article.

* **URL**

  /api/v1/newsletter/article

* **Method:**

  `POST`
  
*  **URL Params**

   **None**
   
*   **Requires Authentication**
 

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
               
                  
                    {
                        "id": 29,
                        "title": "Thomas articles are the best",
                        "content": "programming in Java is awesome!",
                        "published": 0,
                        "author": {
                            "@id": "f3e6db30-468a-421f-b235-481805023fe6",
                            "authorID": 28,
                            "name": "Thomas",
                            "email": "to@yahoo.com",
                            "vpassword": "$2a$10$0akKKzuQwVmLJ6jshoH08uEV.1lp4axZ27jTCLOWnWTDV8sfiiD0a",
                            "phoneNumber": "234701234567",
                            "maritalStatus": "Divorsed",
                            "dateOfBirth": "02-11-210",
                            "articles": [
                                {
                                    "id": 29,
                                    "title": "Thomas articles are the best",
                                    "content": "programming in Java is awesome!",
                                    "published": 0,
                                    "author": "f3e6db30-468a-421f-b235-481805023fe6"
                                }
                            ]
                        }
                    }
                    
 
* **Error Response:**

  * **Code:** 401 Unauthorized <br />
    **Content:** `{ error : "Unathurized" }`
    
* **Sample Call:**

  ```
  {
  	"title": "Thomas articles are the best",
  	"content": "programming in Java is awesome!",
  	"published": 0
  }
  ```



**Find All Published Articles**
----
  Returns list of all published articles.

* **URL**

  /api/v1/newsletter/publishedarticles

* **Method:**

  `GET`
  
*  **URL Params**

   **None**
   
*   **Requires Authentication**
 

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
               
                  
                    [
                        {
                            "id": 26,
                            "title": "my third article",
                            "content": "programming in Java is challenging which makes it better!",
                            "published": 1,
                            "author": {
                                "@id": "47279f0e-a3b4-49f0-83ce-7a2bb1017518",
                                "authorID": 25,
                                "name": "Oladele",
                                "email": "oa@yahoo.com",
                                "vpassword": "$2a$10$jbiFaEmPAHoOBs/PzTUvRugU6TLE2voob38/4qCw3TStf1Vt4LRIm",
                                "phoneNumber": "234701234567",
                                "maritalStatus": "Single",
                                "dateOfBirth": "02-11-2000",
                                "articles": [
                                    {
                                        "id": 26,
                                        "title": "my third article",
                                        "content": "programming in Java is challenging which makes it better!",
                                        "published": 1,
                                        "author": "47279f0e-a3b4-49f0-83ce-7a2bb1017518"
                                    },
                                    {
                                        "id": 27,
                                        "title": "my second article",
                                        "content": "programming in Java is awesome!",
                                        "published": 0,
                                        "author": "47279f0e-a3b4-49f0-83ce-7a2bb1017518"
                                    }
                                ]
                            }
                        }
                    ]
                    
 
* **Error Response:**

  * **Code:** 401 Unauthorized <br />
    **Content:** `{ error : "Unauthorized" }`



**Find All Articles**
----
  Returns all articles in the database

* **URL**

  /api/v1/newsletter/articles

* **Method:**

  `GET`
  
*  **URL Params**

   **None**
   
*   **Requires Authentication**
 

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
                                 
 
* **Error Response:**

  * **Code:** 401 Unauthorized <br />
    **Content:** `{ error : "Unathurized" }`


**Update Article**
----
  Returns updated article alongside its author.

* **URL**

  /api/v1/newsletter/articles/{articleID}

* **Method:**

  `PUT`
  
*  **URL Params**

   *{articleID}*
   
*   **Requires Authentication**
 

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
               
                  
                    {
                        "id": 29,
                        "title": "Thomas articles are the best",
                        "content": "programming in Java is awesome!",
                        "published": 0,
                        "author": {
                            "@id": "561a0f51-6130-4af8-962e-b2b7c7cdc184",
                            "authorID": 28,
                            "name": "Thomas",
                            "email": "to@yahoo.com",
                            "vpassword": "$2a$10$0akKKzuQwVmLJ6jshoH08uEV.1lp4axZ27jTCLOWnWTDV8sfiiD0a",
                            "phoneNumber": "234701234567",
                            "maritalStatus": "Divorsed",
                            "dateOfBirth": "02-11-210",
                            "articles": [
                                {
                                    "id": 29,
                                    "title": "Thomas articles are the best",
                                    "content": "programming in Java is awesome!",
                                    "published": 0,
                                    "author": "561a0f51-6130-4af8-962e-b2b7c7cdc184"
                                }
                            ]
                        }
                    }
                    
 
* **Error Response:**

  * **Code:** 401 Unauthorized <br />
    **Content:** `{ error : "Unathurized" }`
    
* **Sample Call:**

  ```
  {
      "title": "Thomas articles are the best",
      "content": "programming in Python is awesome!",
      "published": 0
  }
  ```
 
 
**Delete Article**
----
  Delete specific article.

* **URL**

  /api/v1/newsletter/articles/{articleID}

* **Method:**

  `DELETE`
  
*  **URL Params**

   *{articleID}*
   
*   **Requires Authentication**
 

* **Data Params**

  None

* **Success Response:**

  * **Code:** 204 <br />
    **Content:** 
               
                  
                    {
                        No Content
                    }
                    
 
* **Error Response:**

  * **Code:** 401 Unauthorized <br />
    **Content:** `{ error : "Unauthorized" }`
    