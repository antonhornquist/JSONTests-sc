JSONTests : Test {
	var
		simpleJSONString,
		simpleJSONStringPrettyPrinted,
		exampleDictionaryWithStringNames,
		exampleDictionaryWithSymbolNames,
		jsonDotOrgExample1, // JSON content example retrieved from json.org/example
		jsonDotOrgExample2, // -"-
		jsonDotOrgExample3, // -"-
		jsonDotOrgExample4 // -"-
	;

	setup {
		simpleJSONString = "{\"address\":{\"city\":\"New York\",\"postalCode\":\"10021-3100\",\"state\":\"NY\",\"streetAddress\":\"21 2nd Street\"},\"age\":25,\"firstName\":\"John\",\"height_cm\":167.64,\"isAlive\":true,\"lastName\":\"Smith\",\"phoneNumbers\":[{\"number\":\"212 555-1234\",\"type\":\"home\"},{\"number\":\"646 555-4567\",\"type\":\"fax\"}]}";

		simpleJSONStringPrettyPrinted = "{
	\"address\": {
		\"city\": \"New York\",
		\"postalCode\": \"10021-3100\",
		\"state\": \"NY\",
		\"streetAddress\": \"21 2nd Street\"
	},
	\"age\": 25,
	\"firstName\": \"John\",
	\"height_cm\": 167.64,
	\"isAlive\": true,
	\"lastName\": \"Smith\",
	\"phoneNumbers\": [
		{
			\"number\": \"212 555-1234\",
			\"type\": \"home\"
		},
		{
			\"number\": \"646 555-4567\",
			\"type\": \"fax\"
		}
	]
}";

		exampleDictionaryWithStringNames = Dictionary[
			("isAlive" -> true),
			("lastName" -> "Smith"),
			("age" -> 25),
			("firstName" -> "John"),
			("height_cm" -> 167.64),
			("phoneNumbers" ->
				[
					Dictionary[
						("number" -> "212 555-1234"),
						("type" -> "home")
					],
					Dictionary[
						("number" -> "646 555-4567"),
						("type" -> "fax")
					]
				]
			),
			("address" ->
				Dictionary[
					("streetAddress" -> "21 2nd Street"),
					("postalCode" -> "10021-3100"),
					("city" -> "New York"),
					("state" -> "NY")
				]
			)
		];

		exampleDictionaryWithSymbolNames = IdentityDictionary[
			(\isAlive -> true),
			(\lastName -> "Smith"),
			(\age -> 25),
			(\firstName -> "John"),
			(\height_cm -> 167.64),
			(\phoneNumbers ->
				[
					IdentityDictionary[
						(\number -> "212 555-1234"),
						(\type -> "home")
					],
					IdentityDictionary[
						(\number -> "646 555-4567"),
						(\type -> "fax")
					]
				]
			),
			(\address ->
				IdentityDictionary[
					(\streetAddress -> "21 2nd Street"),
					(\postalCode -> "10021-3100"),
					(\city -> "New York"),
					(\state -> "NY")
				]
			)
		];

		jsonDotOrgExample1 = "
{
    \"glossary\": {
        \"title\": \"example glossary\",
		\"GlossDiv\": {
            \"title\": \"S\",
			\"GlossList\": {
                \"GlossEntry\": {
                    \"ID\": \"SGML\",
					\"SortAs\": \"SGML\",
					\"GlossTerm\": \"Standard Generalized Markup Language\",
					\"Acronym\": \"SGML\",
					\"Abbrev\": \"ISO 8879:1986\",
					\"GlossDef\": {
                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",
						\"GlossSeeAlso\": [\"GML\", \"XML\"]
                    },
					\"GlossSee\": \"markup\"
                }
            }
        }
    }
}
";

		jsonDotOrgExample2 = "
{\"menu\": {
  \"id\": \"file\",
  \"value\": \"File\",
  \"popup\": {
    \"menuitem\": [
      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},
      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},
      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}
    ]
  }
}}
";

		jsonDotOrgExample3 = "
{\"widget\": {
    \"debug\": \"on\",
    \"window\": {
        \"title\": \"Sample Konfabulator Widget\",
        \"name\": \"main_window\",
        \"width\": 500,
        \"height\": 500
    },
    \"image\": { 
        \"src\": \"Images/Sun.png\",
        \"name\": \"sun1\",
        \"hOffset\": 250,
        \"vOffset\": 250,
        \"alignment\": \"center\"
    },
    \"text\": {
        \"data\": \"Click Here\",
        \"size\": 36,
        \"style\": \"bold\",
        \"name\": \"text1\",
        \"hOffset\": 250,
        \"vOffset\": 100,
        \"alignment\": \"center\",
        \"onMouseUp\": \"sun1.opacity = (sun1.opacity / 100) * 90;\"
    }
}} 
";

		jsonDotOrgExample4 = "
{\"menu\": {
    \"header\": \"SVG Viewer\",
    \"items\": [
        {\"id\": \"Open\"},
        {\"id\": \"OpenNew\", \"label\": \"Open New\"},
        null,
        {\"id\": \"ZoomIn\", \"label\": \"Zoom In\"},
        {\"id\": \"ZoomOut\", \"label\": \"Zoom Out\"},
        {\"id\": \"OriginalView\", \"label\": \"Original View\"},
        null,
        {\"id\": \"Quality\"},
        {\"id\": \"Pause\"},
        {\"id\": \"Mute\"},
        null,
        {\"id\": \"Find\", \"label\": \"Find...\"},
        {\"id\": \"FindAgain\", \"label\": \"Find Again\"},
        {\"id\": \"Copy\"},
        {\"id\": \"CopyAgain\", \"label\": \"Copy Again\"},
        {\"id\": \"CopySVG\", \"label\": \"Copy SVG\"},
        {\"id\": \"ViewSVG\", \"label\": \"View SVG\"},
        {\"id\": \"ViewSource\", \"label\": \"View Source\"},
        {\"id\": \"SaveAs\", \"label\": \"Save As\"},
        null,
        {\"id\": \"Help\"},
        {\"id\": \"About\", \"label\": \"About Adobe CVG Viewer...\"}
    ]
}}
";
	}

	test_itShouldBePossibletoEncodeTheExampleDictionary {
		this.assertEqual( simpleJSONString, exampleDictionaryWithStringNames.asJSON(false) );
	}

	test_itShouldBePossibleToParseTheSimpleJSONString {
		this.assertEqual( exampleDictionaryWithStringNames, JSON.parse(simpleJSONString) );
	}

	test_anEncodedAndParsedDictionaryShouldBeTheSameObject {
		this.assertEqual( exampleDictionaryWithStringNames, JSON.parse(exampleDictionaryWithStringNames.asJSON(false)) );
	}

	test_itShouldBePossibletoEncodeTheExampleDictionaryPrettyPrinted {
		this.assertEqual( simpleJSONStringPrettyPrinted, exampleDictionaryWithStringNames.asJSON(true) );
	}

	test_itShouldBePossibleToParseThePrettyPrintedSimpleJSONString {
		this.assertEqual( exampleDictionaryWithStringNames, JSON.parse(simpleJSONStringPrettyPrinted) );
	}

	test_anEncodedAndParsedDictionaryShouldBeTheSameObjectEvenWhenPrettyPrinted {
		this.assertEqual( exampleDictionaryWithStringNames, JSON.parse(exampleDictionaryWithStringNames.asJSON(true)) );
	}

	test_byDefaultEncodingShouldNotPrettyPrint {
		this.assertEqual( simpleJSONString, exampleDictionaryWithStringNames.asJSON );
	}

	test_aJSONParsedWithNonSymbolNamesShouldEndUpCorrect {
		var parsedJSON;

		parsedJSON = JSON.parse(simpleJSONString, false);

		this.assertEqual(
			Set [ String ],
			parsedJSON.keys.collect { |key| key.class }
		);

		this.assert(
			parsedJSON["phoneNumbers"].every { |dict| dict.keys.collect { |key| key.class } == Set [ String ] }
		);

		this.assertEqual(
			Set [ String ],
			parsedJSON["address"].keys.collect { |key| key.class }
		);
	}

	test_aJSONParsedWithSymbolNamesShouldEndUpCorrect {
		var parsedJSON;

		parsedJSON = JSON.parse(simpleJSONString, true);

		this.assertEqual(
			Set [ Symbol ],
			parsedJSON.keys.collect { |key| key.class }
		);

		this.assert(
			parsedJSON[\phoneNumbers].every { |dict| dict.keys.collect { |key| key.class } == Set [ Symbol ] }
		);

		this.assertEqual(
			Set [ Symbol ],
			parsedJSON[\address].keys.collect { |key| key.class }
		);
	}

	test_byDefaultParsingShouldNotSymbolizeNames {
		var parsedJSON;

		parsedJSON = JSON.parse(simpleJSONString);

		this.assertEqual(
			Set [ String ],
			parsedJSON.keys.collect { |key| key.class }
		);

		this.assert(
			parsedJSON["phoneNumbers"].every { |dict| dict.keys.collect { |key| key.class } == Set [ String ] }
		);

		this.assertEqual(
			Set [ String ],
			parsedJSON["address"].keys.collect { |key| key.class }
		);
	}

	test_itShouldBePossibleToParseJSONDotOrgExample1 {
		this.assertNoErrorThrown {
			JSON.parse(jsonDotOrgExample1)
		};
	}

	test_itShouldBePossibleToParseJSONDotOrgExample2 {
		this.assertNoErrorThrown {
			JSON.parse(jsonDotOrgExample2)
		};
	}

	test_itShouldBePossibleToParseJSONDotOrgExample3 {
		this.assertNoErrorThrown {
			JSON.parse(jsonDotOrgExample3)
		};
	}

	test_itShouldBePossibleToParseJSONDotOrgExample4 {
		this.assertNoErrorThrown {
			JSON.parse(jsonDotOrgExample4)
		};
	}

	test_itShouldNotBePossibleToEncodeInfinityValueToJSON {
		this.assertErrorThrown(Error) {
			(IdentityDictionary[
				(\number -> inf)
			]).asJSON
		};

		this.assertErrorThrown(Error) {
			(IdentityDictionary[
				(\number -> -inf)
			]).asJSON
		};
	}
}
