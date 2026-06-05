#!/bin/bash

# Test script for Product and Category Management System
# This script tests the basic functionality of the application

echo "=== Starting Product and Category Management System Test ==="
echo ""

# Create a test input file
cat > /tmp/test_input.txt << 'EOF'
1
2
Elektronika
4
Burgeļi
1
2
Saldējums
3000
1
1
2
Pūpolu šķīvji
299.99
1
3
5000
1
n
4
2
3
5
5
EOF

echo "Test Input Created"
echo ""
echo "Running application with test inputs..."
echo ""

# Run the application with test inputs
cd /workspaces/java-oop-24DP3JPels-1
rm -f data/produkti.db  # Clean database for fresh test

timeout 30 mvn exec:java -Dexec.mainClass="rvt.App" < /tmp/test_input.txt 2>&1 | tee /tmp/app_output.txt

echo ""
echo "=== Test Complete ==="
echo ""
echo "Checking database file creation..."
if [ -f "data/produkti.db" ]; then
    echo "✓ Database file created successfully"
    ls -lh data/produkti.db
else
    echo "✗ Database file not found"
fi
