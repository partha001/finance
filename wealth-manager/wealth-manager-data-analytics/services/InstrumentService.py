import datetime
import io

import plotly.graph_objects as go
import psycopg2
import yfinance as yf
from flask import send_file

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

        # adjustedClosingPrice = row._1
        # closingPrice = row._2
        # highPrice = row._3
        # lowPrice = row._4
        # openPrice = row._5
        # volume = row._6
        # createTime = datetime.datetime.now()
        # sql = f"insert into WealthManager.DailyPriceMaster (key,  adjcloseprice, closeprice, openprice, high, low, volume, priceDate,createTime) values ('{key}', {adjustedClosingPrice} , {closingPrice}, {openPrice}, {highPrice}, {lowPrice},{volume}, '{priceDate}','{createTime}') on conflict(key, priceDate) do nothing "

        #adjustedClosingPrice = row._1
        closingPrice = row._1
        highPrice = row._2
        lowPrice = row._3
        openPrice = row._4
        volume = row._5
        createTime = datetime.datetime.now()
        sql = f"insert into WealthManager.DailyPriceMaster (key,  closeprice, openprice, high, low, volume, priceDate,createTime) values ('{key}', {closingPrice}, {openPrice}, {highPrice}, {lowPrice},{volume}, '{priceDate}','{createTime}') on conflict(key, priceDate) do nothing "




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



def generateTechnicalChart():
    tickers = ['AAPL','TSLA']
    data = yf.download(tickers, period='1y')

    #filtering data for a particular ticker
    data = data.swaplevel(axis=1).AAPL
    fig = go.Figure(data=[go.Candlestick(
        x=data.index,
        open=data['Open'],
        high=data['High'],
        low=data['Low'],
        close=data['Close']
    )])

    # Update the layout
    fig.update_layout(
        title=f'{tickers[0]} Candle Chart',
        xaxis_title='Date',
        yaxis_title='Price (USD)'
    )

    # Show the plot
    #fig.show()

    # buf = io.BytesIO()
    # fig.write_html(buf, include_plotlyjs='cdn')
    # buf.seek(0)
    # return send_file(buf, mimetype='text/html', as_attachment=False, attachment_filename='chart.html')

    html = fig.to_html(include_plotlyjs='cdn')
    return send_file(io.BytesIO(html.encode()), mimetype='text/html', as_attachment=False, download_name='chart.html')