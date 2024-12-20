import psycopg2
import yfinance as yf
from anyio.streams import file

from config import *


def downloadInstrumentDailyData(ticker:str, key:str, start_date:str, end_date:str):
    conn = None
    cursor = None
    #establishing the connection
    conn = psycopg2.connect(
        database=POSTGRES_DATABASE, user=POSTGRES_USERNAME, password=POSTGRES_PASSWORD, host=POSTGRES_HOST, port= POSTGRES_PORT
    )
    cursor = conn.cursor()
    data = yf.download(ticker, start=start_date, end=end_date)
    for row in data.itertuples():
        # any transformation can be applied here
        priceDate = row.Index.strftime('%Y-%m-%d')
        adjustedClosingPrice = row._1
        closingPrice = row._2
        highPrice = row._3
        lowPrice = row._4
        openPrice = row._5
        volume = row._6
        sql = f"insert into WealthManager.DailyPriceMaster (key,  adjcloseprice, closeprice, openprice, high, low, volume, priceDate) values ('{key}', {adjustedClosingPrice} , {closingPrice}, {openPrice}, {highPrice}, {lowPrice},{volume}, '{priceDate}') on conflict(key, priceDate) do nothing "
        cursor.execute(sql)
    conn.commit()
