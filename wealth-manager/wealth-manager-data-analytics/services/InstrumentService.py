import datetime

import psycopg2
import yfinance as yf

from config import *
from schemas import InstrumentDataDownloadResponseSchema


def downloadInstrumentDailyData(ticker: str, key: str, startDate: str, endDate: str) :
    conn = None
    cursor = None
    # establishing the connection
    conn = psycopg2.connect(
        database=POSTGRES_DATABASE, user=POSTGRES_USERNAME, password=POSTGRES_PASSWORD, host=POSTGRES_HOST,
        port=POSTGRES_PORT
    )
    cursor = conn.cursor()
    data = yf.download(ticker, start=startDate, end=endDate)
    recordsFetched = len(data.index)
    recordsInserted = 0
    for row in data.itertuples():
        # any transformation can be applied here
        priceDate = row.Index.strftime('%Y-%m-%d')
        adjustedClosingPrice = row._1
        closingPrice = row._2
        highPrice = row._3
        lowPrice = row._4
        openPrice = row._5
        volume = row._6
        createTime = datetime.datetime.now()
        sql = f"insert into WealthManager.DailyPriceMaster (key,  adjcloseprice, closeprice, openprice, high, low, volume, priceDate,createTime) values ('{key}', {adjustedClosingPrice} , {closingPrice}, {openPrice}, {highPrice}, {lowPrice},{volume}, '{priceDate}','{createTime}') on conflict(key, priceDate) do nothing "
        cursor.execute(sql)
        recordsInserted += cursor.rowcount
    conn.commit()

    #creating the response
    response = InstrumentDataDownloadResponseSchema()
    response.key = key
    response.ticker = ticker
    response.start_date = startDate
    response.end_date = endDate
    response.recordsFetched = recordsFetched
    response.recordsInserted = recordsInserted
    return response
