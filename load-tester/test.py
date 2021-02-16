from locust import HttpUser, task, between
from locust.exception import InterruptTaskSet

import logging

class Test(HttpUser):
    wait_time = between(0, 1)

    @task
    def index(self):
        logging.info("Get / from slow server...")
        self.client.get("/")
