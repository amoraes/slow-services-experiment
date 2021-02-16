# this will test the slow service just to validate it can consistently return responses with 30s does not matter
# what the load is
locust --host=http://localhost:8001 --users 2000 --spawn-rate=2000 -f test.py