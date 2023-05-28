package edu.singaporetech.grocerylist

object Constants {

    fun getGroceries(): ArrayList<GroceryModel> {
        val groceryList = ArrayList<GroceryModel>()
        val grocery1 = GroceryModel("Cilantro", false)
        groceryList.add(grocery1)
        val grocery2 = GroceryModel("Beans", false)
        groceryList.add(grocery2)
        val grocery3 = GroceryModel("Cheese", false)
        groceryList.add(grocery3)
        val grocery4 = GroceryModel("Oil", false)
        groceryList.add(grocery4)
        val grocery5 = GroceryModel("Tomato", false)
        groceryList.add(grocery5)
        val grocery6 = GroceryModel("Salt", false)
        groceryList.add(grocery6)
        val grocery7 = GroceryModel("Pepper", false)
        groceryList.add(grocery7)
        val grocery8 = GroceryModel("Flour", false)
        groceryList.add(grocery8)
        val grocery9 = GroceryModel("Corn", false)
        groceryList.add(grocery9)
        val grocery10 = GroceryModel("Garlic", false)
        groceryList.add(grocery10)
        val grocery11 = GroceryModel("Lime", false)
        groceryList.add(grocery11)
        val grocery12 = GroceryModel("Onion", false)
        groceryList.add(grocery12)
        val grocery13 = GroceryModel("Rice", false)
        groceryList.add(grocery13)
        val grocery14 = GroceryModel("Cabbage", false)
        groceryList.add(grocery14)
        val grocery15 = GroceryModel("Avocado", false)
        groceryList.add(grocery15)
        return groceryList
    }
}