Command Run question1 jar
java -jar question1.jar D:\CodingworkArea\SampleMeetingInput.txt

Command to run question2 
java -jar question2.jar D:\CodingworkArea\SamplePlanSelection.txt voice,database,admin

BonusQuestion

API URL : http://localhost:8080/api/plans
Type:POST
Request{
file:"D:\CodingworkArea\SamplePlanSelection.txt",
"featuresRequired":"voice,database,admin"
}
Response
{
    "planCost": 235,
    "plansToBeSelected": "PLAN1,PLAN4"
}

Swagger URL : http://localhost:8080/swagger-ui/index.html


Docker commands to pull and run Image
docker pull jayasreegvn/planfinder:1.0.0
docker run -p 8080:8080 --name samplepoccontainer jayasreegvn/planfinder:1.0.0