import csv
import pandas as pd

import psycopg2

from config import *

def printHello():

    conn = None
    cursor = None

    #establishing the connection
    conn = psycopg2.connect(
        database=POSTGRES_DATABASE, user=POSTGRES_USERNAME, password=POSTGRES_PASSWORD, host=POSTGRES_HOST, port= POSTGRES_PORT
    )

    cursor = conn.cursor()

    #Executing an MYSQL function using the execute() method
    cursor.execute("select * from wealthmanager.assetmaster")
    result = cursor.fetchall()

    df = pd.DataFrame.from_records(result, columns=[x[0] for x in cursor.description])
    df.to_csv('./test.csv', index=False)




    #Closing the connection
    conn.close()


    print(secretKey)
    print("hello world")
    return result