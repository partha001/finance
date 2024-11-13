from base64 import encodebytes
from io import BytesIO

import matplotlib
import matplotlib.pyplot as plt
import pandas as pd
import psycopg2
from anyio.streams import file

from config import *


def getAssetChart():

    conn = None
    cursor = None

    matplotlib.use('agg')

    #establishing the connection
    conn = psycopg2.connect(
        database=POSTGRES_DATABASE, user=POSTGRES_USERNAME, password=POSTGRES_PASSWORD, host=POSTGRES_HOST, port= POSTGRES_PORT
    )

    cursor = conn.cursor()
    cursor.execute("select * from wealthmanager.assetmaster")
    result = cursor.fetchall()
    df = pd.DataFrame.from_records(result, columns=[x[0] for x in cursor.description])
    imported_assets = df[df["username"]=="partha"]
    imported_assets.assign(valuationdate = pd.to_datetime(imported_assets["valuationdate"]))

    pivotedAssets =imported_assets.pivot(index="valuationdate", columns="assetname", values=['amount'])
    dftemp = pivotedAssets
    mylist = list(dftemp.columns)
    dftemp['total'] = dftemp[dftemp.columns].sum(axis=1)
    #df.to_csv('./test.csv', index=False) #optionally to save it into csv
    #Closing the connection
    conn.close()

    fig = plt.subplots(figsize=(15, 7))
    plt.xlabel('Date', fontsize=15)
    plt.ylabel('INR', fontsize=15)
    plt.title('assets-vs-time', fontsize=20)

    for item in list(dftemp.columns)[:-1:]:
        plt.plot(dftemp.index, dftemp[item], label= item[1])

    plt.plot(dftemp.index, dftemp[('total')], label='total')
    plt.legend()
    #plt.savefig('test.jpeg')
    # plt.show()
    # plt.close()

    figdata = BytesIO()
    plt.savefig(figdata, format='png')
    encoded_img = encodebytes(figdata.getvalue()).decode('ascii')
    plt.close()

    print('file saved successfully')
    return encoded_img


def sendimage() :
    data = file.read()