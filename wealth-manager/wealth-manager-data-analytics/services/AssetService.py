import psycopg2

from config import *

def printHello():

    conn = None
    cursor = None

    #establishing the connection
    print('postgreshost',POSTGRES_HOST)
    conn = psycopg2.connect(
        database=POSTGRES_DATABASE, user=POSTGRES_USERNAME, password=POSTGRES_PASSWORD, host=POSTGRES_HOST, port= POSTGRES_PORT
    )

    cursor = conn.cursor()

    #Executing an MYSQL function using the execute() method
    cursor.execute("select * from wealthmanager.assetmaster")
    result = cursor.fetchall()

    # Fetch a single row using fetchone() method.
    data = cursor.fetchone()
    print("Connection established to: ",data)

    #Closing the connection
    conn.close()


    print(secretKey)
    print("hello world")
    return result