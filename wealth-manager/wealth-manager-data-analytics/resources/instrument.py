from flask import request
from flask.views import MethodView
from flask_smorest import Blueprint

from schemas import InstrumentDataDownloadRequestSchema
from services.InstrumentService import downloadInstrumentDailyData

blp = Blueprint("instrument", __name__, description="operations on instruments")


@blp.route("/instruments/downloadDailyData")
class Instruments(MethodView):
    def get(self):
        ticker = request.args.get('ticker')
        key = request.args.get('key')
        start_date = request.args.get('start_date')
        end_date = request.args.get('end_date')
        downloadInstrumentDailyData(ticker, key, start_date, end_date)
        return 200

    @blp.arguments(InstrumentDataDownloadRequestSchema)
    @blp.response(200)
    def post(self, payload):
        print(payload)
        key = payload.get('key')
        ticker = payload.get('ticker')
        start_date = payload.get('start_date')
        end_date = payload.get('end_date')
        downloadInstrumentDailyData(ticker, key, start_date, end_date)
