from base64 import encodebytes
from io import BytesIO

import matplotlib
import matplotlib.pyplot as plt
import pandas as pd
import psycopg2
from anyio.streams import file

from config import *


def getDividendChartByYear():

    conn = None
    cursor = None

    #establishing the connection
    conn = psycopg2.connect(
        database=POSTGRES_DATABASE, user=POSTGRES_USERNAME, password=POSTGRES_PASSWORD, host=POSTGRES_HOST, port= POSTGRES_PORT
    )

    matplotlib.use('agg')
    cursor = conn.cursor()
    cursor.execute("select * from wealthmanager.dividendmaster")
    result = cursor.fetchall()
    df = pd.DataFrame.from_records(result, columns=[x[0] for x in cursor.description])
    df["year"] = df["dividendyear"].astype(str)
    temp = df[['year','dividendamount']]
    temp = temp.groupby("year").sum("dividendamount")
    conn.close()

    dividendByYear = temp['dividendamount'].to_dict()
    plt.style.use("seaborn-v0_8-whitegrid")
    fig, ax = plt.subplots(figsize=(10, 6))
    keys = list(dividendByYear.keys())
    values = list(dividendByYear.values())
    ax.bar(keys, values)
    ax.set(title = "Dividend per year",
           ylabel= "Dividend amount (INR)")

    #the for is for displying the number on top
    for i in range(len(keys)):
        plt.text(i,values[i],values[i])


    figdata = BytesIO()
    plt.savefig(figdata, format='png')
    encoded_img = encodebytes(figdata.getvalue()).decode('ascii')
    plt.close()

    print('file saved successfully')
    return encoded_img



def getDividendChartByQuarter():

    conn = None
    cursor = None

    #establishing the connection
    conn = psycopg2.connect(
        database=POSTGRES_DATABASE, user=POSTGRES_USERNAME, password=POSTGRES_PASSWORD, host=POSTGRES_HOST, port= POSTGRES_PORT
    )

    matplotlib.use('agg')
    cursor = conn.cursor()
    cursor.execute("select * from wealthmanager.dividendmaster")
    result = cursor.fetchall()
    df = pd.DataFrame.from_records(result, columns=[x[0] for x in cursor.description])


    df["yearText"] = df["dividendyear"].astype(str)
    df["quarterText"] = df["quarter"].map({1: 'Q1', 2: 'Q2', 3: 'Q3', 4:'Q4'})
    temp = df[['yearText' ,'quarterText','dividendamount']]
    temp = temp.rename(columns={'yearText': 'year', 'quarterText': 'quarter'})

    #the trick lies in resetting the index which flattens the structure
    temp = temp.groupby(['year', 'quarter']).sum('dividendamount').apply(list).reset_index()
    list(temp['year'])
    list(temp['quarter'])
    list(temp['dividendamount'])

    data = {
        'Year': list(temp['year']),
        'Quarter':list(temp['quarter']),
        'Amount': list(temp['dividendamount'])
    }

    #data
    df = pd.DataFrame(data)
    pivot_df = df.pivot(index='Year', columns='Quarter', values='Amount').fillna(0)

    # Step 4: Plotting
    plt.style.use("seaborn-v0_8-whitegrid")
    pivot_df.plot(kind='bar', figsize=(10, 6))
    plt.xlabel('Quarters-Year-wise')
    plt.ylabel('Dividend Amount')
    plt.title('Amount of dividend')
    plt.xticks(rotation=0)
    plt.legend(title='Quarters')

    plt.tight_layout()  # Adjust layout for better fit

    figdata = BytesIO()
    plt.savefig(figdata, format='png')
    encoded_img = encodebytes(figdata.getvalue()).decode('ascii')
    plt.close()

    print('file saved successfully')
    return encoded_img



def sendimage() :
    data = file.read()