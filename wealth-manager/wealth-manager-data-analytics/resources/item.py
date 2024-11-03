import uuid
from flask import request
from flask.views import MethodView
from flask_smorest import Blueprint, abort
from db import stores, items

blp = Blueprint("items",__name__,description="Operations on item")

@blp.route("/item")
class Item(MethodView):

    def get(self):
        return {"items": list(items.values())}, 200


    def post(self):
        item_data = request.get_json()
        if (
                "price" not in item_data
                or "storeId" not in item_data
                or "name" not in item_data
        ):
            abort(
                400,
                message="Bad request. ensure price, storeId and name is present included in json payload"
            )
        if item_data["storeId"] not in stores:
            #return "storeId:{} is not found ".format(item_data["storeId"]), 404
            abort(404, message="storeId:{} is not found ".format(item_data["storeId"]))

        itemId = uuid.uuid4().hex
        item = {**item_data, "itemId": itemId}
        items[itemId] = item
        return item, 201


@blp.route("/item/<string:itemId>")
class ItemIdOperations(MethodView):

    def get(self,itemId):
        try:
            return items[itemId]
        except KeyError:
            abort(404, message="item with itemId = {} not found".format(itemId))


    def delete(self,itemId):
        try:
            del items[itemId]
            return {"message":"item deleted successfully "},200
        except KeyError:
            abort(404, message="item with itemId = {} not found".format(itemId))


    def put(self,itemId):
        item_data = request.get_json()
        if "price" not in item_data or "name" not in item_data:
            abort(400, message= "Bad request. Ensure price and name are included in json payload")

        try:
            item = items[itemId]
            item |= item_data #this dictionary operator does in place replacement
            return item, 200
        except KeyError:
            abort(404, message="item with itemId = {} not found".format(itemId))

