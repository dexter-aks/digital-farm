# digital-farm
Application to create field and provide weather details using https://agromonitoring.com/api

#API Endpoints
### GET - /fields/{fieldId}
- Get the field details by field id
### POST - /fields
- Create the field with boundary details
- Sample
{
    "name": "Rice Field",
    "countryCode":"DEU",
    "geo_json": {
        "type": "Feature",
        "properties": {},
        "geometry": {
            "type": "Polygon",
            "coordinates": [
                [
                    [
                        -5.553604888914691,
                        33.88229680420605
                    ],
                    [
                        -5.5516736984239685,
                        33.88229680420605
                    ],
                    [
                        -5.5516736984239685,
                        33.88372189858022
                    ],
                    [
                        -5.555965232847882,
                        33.88390003370375
                    ],
                    [
                        -5.555965232847882,
                        33.88229680420605
                    ],
                    [
                        -5.553604888914691,
                        33.88229680420605
                    ]
                ]
            ]
        }
    }
}
### PUT - /fields/{fieldId}
- Update field by id which take complete entity
### DELETE - /fields/{fieldId}
- Delete the field by id
### GET - /fields/{fieldId}/weather
- Get 7 days weather details of the field by id

### Technical Stack
1. WEB - SpringBoot(JAVA)
2. Database - Postgres
3. Testing - TestContainers + JUnit 5
4. Execution - Docker Container
5. Project Management - Gradle
6. Runtime Env - Java 11 
7. Containerized - Docker

### Build 
- gradle clean build

### Run 
- docker-compose up

### Assumptions
1. One Field - One Boundary(Polygon)
2. Field Name can be duplicate
3. Polygon created in Agro API for Testing is not deleted
4. API Key is unmasked
5. Only Polygon ID from Agro API is saved in database, rest of details is fetched directly




 
