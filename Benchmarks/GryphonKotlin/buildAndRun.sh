clear



echo "Building..."

if kotlinc -include-runtime -d kotlin.jar *.kt
then
	echo "✅ Done."
	echo ""
else
	echo "🚨 Failed to build."
    alerter -timeout 3 -sound "Basso" \
        -message "Build failed! 🚨"
	exit $?
fi



echo "Running tests..."

if java -jar kotlin.jar -test
then
	echo "✅ Done."
	echo ""
    alerter -timeout 3 -sound "Glass" \
        -message "Tests ran successfully! ✅"
else
	echo "🚨 Failed to run tests."
    alerter -timeout 3 -sound "Basso" \
        -message "Tests failed! 🚨"
	exit $?
fi
