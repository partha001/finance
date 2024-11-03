import os

from dotenv import load_dotenv

load_dotenv()

secretKey = os.getenv('secretKey')
POSTGRES_HOST=os.getenv('POSTGRES_HOST')
POSTGRES_USERNAME=os.getenv('POSTGRES_USERNAME')
POSTGRES_PASSWORD=os.getenv('POSTGRES_PASSWORD')
POSTGRES_PORT=os.getenv('POSTGRES_PORT')
POSTGRES_DATABASE=os.getenv('POSTGRES_DATABASE')

