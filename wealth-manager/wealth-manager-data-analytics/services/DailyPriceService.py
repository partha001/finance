from pandas import DataFrame
from sqlalchemy import create_engine
from config import *
import pandas as pd

def dailyPriceMaterToDataframe(key:str) -> DataFrame :
    # username = 'postgres'
    # password = 'password'
    # host = 'localhost'
    # port = 5432
    # database = 'mydatabase'
    #database=POSTGRES_DATABASE, user=POSTGRES_USERNAME, password=POSTGRES_PASSWORD, host=POSTGRES_HOST, port=POSTGRES_PORT

    engine = create_engine(f'postgresql://{POSTGRES_USERNAME}:{POSTGRES_PASSWORD}@{POSTGRES_HOST}:{POSTGRES_PORT}/{POSTGRES_DATABASE}')
    #sql = f'select pricedate, adjcloseprice, closeprice, openprice, high, low, volume from wealthmanager.dailypricemaster d'

    sql = f"select pricedate, adjcloseprice, closeprice, openprice, high, low, volume from wealthmanager.dailypricemaster d where d.key='{key}' "

    #df = pd.read_sql_query(sql, engine)
    df = pd.read_sql_query(sql, engine, index_col=['pricedate'])

    df.index.name='Date'
    df.columns.name='Price'
    df = df.rename(columns={'closeprice': 'Close', 'openprice':'Open','high':'High','low':'Low', 'volume':'Volume'})
    return df