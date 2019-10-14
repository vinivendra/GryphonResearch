
swiftc \
    -dump-ast \
    -output-file-map=output-file-map.json \
    -module-name="Benchmarks" \
    Benchmarks/gryphonSpectral-norm.swift \
    Benchmarks/gryphonBinary-trees.swift \
    Benchmarks/gryphonFannkuch-redux.swift \
    Benchmarks/gryphonMandelbrot.swift \
    Benchmarks/gryphonN-body.swift

path/to/Gryphon \
    -output-file-map=output-file-map.json \
    -indentation=4 \
    -emit-kotlin \
    Benchmarks/gryphonSpectral-norm.swift \
    Benchmarks/gryphonBinary-trees.swift \
    Benchmarks/gryphonFannkuch-redux.swift \
    Benchmarks/gryphonMandelbrot.swift \
    Benchmarks/gryphonN-body.swift
