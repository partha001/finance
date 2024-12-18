import io
from base64 import encodebytes

import PIL.Image
from flask.views import MethodView
from flask_smorest import Blueprint, abort

from services.DividendService import getDividendChartByYear,getDividendChartByQuarter

blp = Blueprint("dividends",__name__,description="operations on dividends")

# @blp.route("/dividends")
# class Dividends(MethodView):


@blp.route("/dividends/dividendSummaryByYear")
class DividendsChartOperations(MethodView):

    def get(self):
        test = getDividendChartByYear()
        return test,200

@blp.route("/dividends/dividendSummaryByQuarter")
class DividendsChartOperationsByQuarter(MethodView):

    def get(self):
        test = getDividendChartByQuarter()
        return test,200

    # def get(self):
    #     try:
    #         pil_img = PIL.Image.open("test.png", "r") # reads the PIL image
    #         byte_arr = io.BytesIO()
    #         pil_img.save(byte_arr, format='PNG') # convert the PIL image to byte array
    #         encoded_img = encodebytes(byte_arr.getvalue()).decode('ascii') # encode as base64
    #         return encoded_img
    #     except KeyError:
    #         abort(404, message="item with itemId =  not found")





