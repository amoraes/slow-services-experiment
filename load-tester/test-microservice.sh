# this will test the microservice to validate if it the microservice can cope with it
locust --host=http://localhost:8080 --users 2000 --spawn-rate=2000 -f test.py